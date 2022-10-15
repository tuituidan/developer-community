package com.tuituidan.openhub.mapper;

import com.tuituidan.openhub.pojo.entity.User;
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
public interface UserMapper extends Mapper<User>, InsertListMapper<User>, IdsMapper<User> {

}
