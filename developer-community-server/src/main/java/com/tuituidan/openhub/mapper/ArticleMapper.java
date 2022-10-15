package com.tuituidan.openhub.mapper;

import com.tuituidan.openhub.pojo.dto.ArticleSearchDTO;
import com.tuituidan.openhub.pojo.entity.Article;
import com.tuituidan.openhub.pojo.vo.ArticleVO;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.additional.insert.InsertListMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;

/**
 * ArticleMapper.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/4
 */
public interface ArticleMapper extends Mapper<Article>, InsertListMapper<Article>, IdsMapper<Article> {

    /**
     * selectList
     *
     * @param searchParams searchParams
     * @return List
     */
    List<ArticleVO> selectList(ArticleSearchDTO searchParams);

    /**
     * 设置是否精品和是否置顶
     *
     * @param id id
     * @param type type
     * @param value value
     */
    void updateTopOrValuable(@Param("id") String id,
            @Param("type") String type,
            @Param("value") String value);
}
