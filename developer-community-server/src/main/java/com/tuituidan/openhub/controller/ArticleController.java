package com.tuituidan.openhub.controller;

import com.tuituidan.openhub.consts.Consts;
import com.tuituidan.openhub.pojo.dto.ArticleDTO;
import com.tuituidan.openhub.pojo.dto.ArticleSearchDTO;
import com.tuituidan.openhub.pojo.vo.ArticleVO;
import com.tuituidan.openhub.service.ArticleService;
import com.tuituidan.tresdin.datatranslate.annotation.DataTranslate;
import com.tuituidan.tresdin.mybatis.bean.PageParam;
import com.tuituidan.tresdin.page.PageData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * IndexController.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/9/25
 */
@Api(tags = "文章")
@RestController
@RequestMapping(Consts.API_V1)
public class ArticleController {

    @Resource
    private ArticleService articleService;

    /**
     * list
     *
     * @param pageParam pageParam
     * @param searchParams searchParams
     * @return PageData
     */
    @ApiOperation("分页查询文章列表")
    @DataTranslate
    @GetMapping("/article")
    public PageData<List<ArticleVO>> list(PageParam pageParam, ArticleSearchDTO searchParams) {
        return articleService.list(pageParam, searchParams);
    }

    /**
     * get
     *
     * @param id id
     * @return ArticleVO
     */
    @ApiOperation("获取单个文章")
    @DataTranslate
    @GetMapping("/article/{id}")
    public ArticleVO get(@PathVariable("id") String id) {
        return articleService.get(id);
    }

    /**
     * create
     *
     * @param article article
     * @return String
     */
    @ApiOperation("新建文章")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/article")
    public String create(@RequestBody ArticleDTO article) {
        return articleService.create(article);
    }

    /**
     * update
     *
     * @param id id
     * @param article article
     */
    @ApiOperation("修改文章")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/article/{id}")
    public void update(@PathVariable("id") String id, @RequestBody ArticleDTO article) {
        articleService.update(id, article);
    }

    /**
     * 设置是否精品和是否置顶
     *
     * @param id id
     * @param type type
     * @param value value
     */
    @ApiOperation("设置是否精品和是否置顶")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/article/{id}/{type:top|valuable}/{value}")
    public void setArticleValuable(@PathVariable("id") String id,
            @PathVariable("type") String type,
            @PathVariable("value") String value) {
        articleService.setTopOrValuable(id, type, value);
    }
}
