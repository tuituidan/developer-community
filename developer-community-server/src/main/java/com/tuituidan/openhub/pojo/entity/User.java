package com.tuituidan.openhub.pojo.entity;

import java.io.Serializable;
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
@Table(name = "dc_user", schema = "business")
public class User implements Serializable {

    private static final long serialVersionUID = -3404101162871398085L;

    @Id
    private String id;

    private String name;

    private String avatar;

}
