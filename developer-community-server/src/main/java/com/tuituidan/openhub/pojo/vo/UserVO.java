package com.tuituidan.openhub.pojo.vo;

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
public class UserVO {

    private String id;

    private String name;

    private String avatar;
}
