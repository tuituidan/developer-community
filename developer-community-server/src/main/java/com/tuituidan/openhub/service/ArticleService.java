package com.tuituidan.openhub.service;

import com.tuituidan.openhub.consts.DictConsts;
import com.tuituidan.openhub.mapper.ArticleMapper;
import com.tuituidan.openhub.mapper.AttachFileMapper;
import com.tuituidan.openhub.pojo.dto.ArticleDTO;
import com.tuituidan.openhub.pojo.dto.ArticleSearchDTO;
import com.tuituidan.openhub.pojo.entity.Article;
import com.tuituidan.openhub.pojo.entity.AttachFile;
import com.tuituidan.openhub.pojo.vo.ArticleVO;
import com.tuituidan.openhub.util.MarkdownUtils;
import com.tuituidan.openhub.util.SecurityUtils;
import com.tuituidan.tresdin.dictionary.IDictionaryService;
import com.tuituidan.tresdin.dictionary.bean.DictInfo;
import com.tuituidan.tresdin.dictionary.bean.DictTree;
import com.tuituidan.tresdin.mybatis.QueryHelper;
import com.tuituidan.tresdin.mybatis.bean.PageParam;
import com.tuituidan.tresdin.page.PageData;
import com.tuituidan.tresdin.util.BeanExtUtils;
import com.tuituidan.tresdin.util.StringExtUtils;
import com.tuituidan.tresdin.util.TransactionUtils;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * ArticleService.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/4
 */
@Service
@Slf4j
public class ArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private IDictionaryService dictionaryService;

    @Resource
    private AttachFileMapper attachFileMapper;

    /**
     * list
     *
     * @param pageParam pageParam
     * @param searchParams searchParams
     * @return PageData
     */
    public PageData<List<ArticleVO>> list(PageParam pageParam, ArticleSearchDTO searchParams) {
        return QueryHelper.queryPage(pageParam, () -> articleMapper.selectList(searchParams));
    }

    /**
     * get
     *
     * @param id id
     * @return ArticleVO
     */
    public ArticleVO get(String id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        return BeanExtUtils.convert(article, ArticleVO::new);
    }

    /**
     * create
     *
     * @param article article
     * @return String
     */
    public String create(ArticleDTO article) {
        Article entity = toEntity(article);
        entity.setId(StringExtUtils.getUuid())
                .setTop("2")
                .setValuable("2")
                .setVisits(0)
                .setApproves(0)
                .setCollects(0)
                .setComments(0);

        TransactionUtils.execute(() -> {
            articleMapper.insertSelective(entity);
            if (ArrayUtils.isNotEmpty(article.getFilesIds())) {
                Arrays.stream(article.getFilesIds())
                        .map(fileId -> new AttachFile().setId(fileId).setBelongId(entity.getId()))
                        .forEach(fileItem -> attachFileMapper.updateByPrimaryKeySelective(fileItem));
            }
        });
        return entity.getId();
    }

    /**
     * update
     *
     * @param id id
     * @param article article
     */
    public void update(String id, ArticleDTO article) {
        Article entity = toEntity(article);
        entity.setId(id);
        TransactionUtils.execute(() -> {
            articleMapper.updateByPrimaryKeySelective(entity);
            saveAttachFiles(id, article.getFilesIds());
        });
    }

    /**
     * 设置是否精品和是否置顶
     *
     * @param id id
     * @param type type
     * @param value value
     */
    public void setTopOrValuable(String id, String type, String value) {
        articleMapper.updateTopOrValuable(id, type, value);
    }

    private void saveAttachFiles(String belongId, String[] fileIds) {
        List<AttachFile> exists = attachFileMapper.select(new AttachFile().setBelongId(belongId));
        if (CollectionUtils.isEmpty(exists)) {
            if (ArrayUtils.isEmpty(fileIds)) {
                return;
            }
            Arrays.stream(fileIds).map(fileId -> new AttachFile().setId(fileId).setBelongId(belongId))
                    .forEach(fileItem -> attachFileMapper.updateByPrimaryKeySelective(fileItem));
            return;
        }
        Set<String> existIds = exists.stream().map(AttachFile::getId).collect(Collectors.toSet());
        Set<String> saveIds = ArrayUtils.isEmpty(fileIds)
                ? new HashSet<>() : Arrays.stream(fileIds).collect(Collectors.toSet());
        Set<String> deleteIds = new HashSet<>(existIds);
        // 已经存在的移除掉需要保存的剩下的就是需要删除的
        deleteIds.removeAll(saveIds);
        if (CollectionUtils.isNotEmpty(deleteIds)) {
            attachFileMapper.deleteByIds(StringUtils.join(deleteIds));
        }
        // 需要保存移除掉已经存在的就是新增的
        saveIds.removeAll(existIds);
        if (CollectionUtils.isNotEmpty(existIds)) {
            existIds.stream().map(fileId -> new AttachFile().setId(fileId).setBelongId(belongId))
                    .forEach(fileItem -> attachFileMapper.updateByPrimaryKeySelective(fileItem));
        }
    }

    private Article toEntity(ArticleDTO article) {
        Article entity = BeanExtUtils.convert(article, Article::new);
        entity.setAuthor(SecurityUtils.getCurrentUser().getId());
        entity.setIntroduction(MarkdownUtils.getIntroduction(entity.getContentMarkdown()));
        if (StringUtils.isBlank(entity.getFirstImage())) {
            entity.setFirstImage(MarkdownUtils.getFirstImage(entity.getContentHtml()));
        }
        entity.setTopics(getTopics(entity.getTags()));
        return entity;
    }

    private String[] getTopics(String[] tags) {
        List<DictTree> treeList = dictionaryService.getDictTreeByType(DictConsts.DICT_TYPE_TAG);
        Map<String, String> dictMap = treeList.stream().map(DictTree::getChildren).flatMap(List::stream)
                .collect(Collectors.toMap(DictInfo::getId, DictInfo::getPid));
        return Arrays.stream(tags).map(dictMap::get).distinct().toArray(String[]::new);
    }

}
