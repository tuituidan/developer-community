package com.tuituidan.openhub.util;

import com.tuituidan.openhub.pojo.vo.UserVO;
import lombok.experimental.UtilityClass;

/**
 * SecurityUtils.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/12
 */
@UtilityClass
public class SecurityUtils {

    /**
     * getCurrentUser
     *
     * @return UserVO
     */
    public static UserVO getCurrentUser() {
        return new UserVO().setId("64675b7cc5dec362baf57818ce666ad0").setName("朱军函");
    }

}
