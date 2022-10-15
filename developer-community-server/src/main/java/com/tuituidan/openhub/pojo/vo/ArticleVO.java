package com.tuituidan.openhub.pojo.vo;

import com.tuituidan.openhub.consts.DictConsts;
import com.tuituidan.openhub.translator.user.UserAvatar;
import com.tuituidan.openhub.translator.user.UserName;
import com.tuituidan.tresdin.datatranslate.translator.dict.DictType;
import java.time.LocalDateTime;
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
public class ArticleVO {

    private String id;

    private String title;

    @UserName
    @UserAvatar
    private String author;

    @DictType(DictConsts.DICT_TYPE_TAG)
    private String[] topics;

    @DictType(DictConsts.DICT_TYPE_TAG)
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
