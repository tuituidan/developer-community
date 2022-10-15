package com.tuituidan.openhub.mapper;

import com.tuituidan.openhub.pojo.entity.AttachFile;
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
public interface AttachFileMapper extends Mapper<AttachFile>, InsertListMapper<AttachFile>, IdsMapper<AttachFile> {

}
