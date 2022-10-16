package com.tuituidan.openhub.pojo.entity;

import com.tuituidan.tresdin.mybatis.bean.IEntity;
import java.time.LocalDateTime;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * AttachFile.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/4
 */
@Setter
@Getter
@Accessors(chain = true)
@Table(name = "dc_attach_file", schema = "business")
public class AttachFile implements IEntity<AttachFile> {

    private static final long serialVersionUID = -8842768942702979493L;

    @Id
    private String id;

    private String belongId;

    private String name;

    private String path;

    private Long size;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
