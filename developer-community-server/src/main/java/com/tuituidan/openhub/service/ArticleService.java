package com.tuituidan.openhub.service;

import com.tuituidan.openhub.consts.DictConsts;
import com.tuituidan.openhub.mapper.ArticleMapper;
import com.tuituidan.openhub.pojo.dto.ArticleDTO;
import com.tuituidan.openhub.pojo.dto.ArticleSearchDTO;
import com.tuituidan.openhub.pojo.entity.Article;
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
import com.tuituidan.tresdin.util.TransactionUtils;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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
    private AttachFileService attachFileService;

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
        entity.setTop("2")
                .setValuable("2")
                .setVisits(0)
                .setApproves(0)
                .setCollects(0)
                .setComments(0);

        TransactionUtils.execute(() -> {
            articleMapper.insertSelective(entity);
            attachFileService.saveAttachFiles(entity.getId(), article.getFilesIds());
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
            attachFileService.saveAttachFiles(id, article.getFilesIds());
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
