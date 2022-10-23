package com.tuituidan.openhub.service;

import com.tuituidan.openhub.mapper.UserMapper;
import com.tuituidan.openhub.pojo.entity.User;
import com.tuituidan.openhub.pojo.vo.UserVO;
import com.tuituidan.tresdin.util.BeanExtUtils;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * ArticleService.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/4
 */
@Service
@Slf4j
@CacheConfig(cacheNames = "users")
public class UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * getUser
     *
     * @param id id
     * @return UserVO
     */
    @Cacheable(key = "#id")
    public UserVO getUser(String id) {
        User user = userMapper.selectByPrimaryKey(id);
        return BeanExtUtils.convert(user, UserVO::new);
    }

}
