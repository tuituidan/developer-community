package com.tuituidan.openhub.controller;

import com.tuituidan.openhub.pojo.vo.UserVO;
import com.tuituidan.openhub.util.SecurityUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * UserController.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/26
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @ApiOperation("登录用户信息")
    @GetMapping("/current")
    public UserVO getCurrentUser() {
        return SecurityUtils.getCurrentUser();
    }

}
