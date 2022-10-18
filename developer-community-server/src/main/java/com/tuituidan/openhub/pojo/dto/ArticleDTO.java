package com.tuituidan.openhub.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@ToString
public class ArticleDTO {

    private String title;

    private String[] tags;

    private String firstImage;

    private String contentMarkdown;

    private String contentHtml;

    private String[] filesIds;

    private String publish;

}
