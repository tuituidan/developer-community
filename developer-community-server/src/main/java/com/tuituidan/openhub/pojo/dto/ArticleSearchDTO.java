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
public class ArticleSearchDTO {

    private String[] id;

    private String keywords;

    private String topics;

    private String tags;

}
