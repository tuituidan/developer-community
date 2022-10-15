package com.tuituidan.openhub.pojo.entity;

import com.tuituidan.tresdin.mybatis.bean.IEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * DcArticle.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/4
 */
@Setter
@Getter
@Accessors(chain = true)
@Table(name = "dc_article", schema = "business")
public class Article implements IEntity<Article> {

    private static final long serialVersionUID = 1757626423700252637L;

    @Id
    private String id;

    private String title;

    private String author;

    /**
     * 数组不设置Column注解无法识别
     */
    @Column(name = "topics")
    private String[] topics;

    @Column(name = "tags")
    private String[] tags;

    private String firstImage;

    private String introduction;

    private String contentMarkdown;

    private String contentHtml;

    private String top;

    private String valuable;

    private Integer visits;

    private Integer collects;

    private Integer approves;

    private Integer comments;

    private LocalDateTime replyTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;
}
