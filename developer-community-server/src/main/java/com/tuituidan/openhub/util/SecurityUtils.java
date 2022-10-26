package com.tuituidan.openhub.util;

import com.tuituidan.openhub.pojo.vo.UserVO;
import com.tuituidan.tresdin.util.BeanExtUtils;
import lombok.experimental.UtilityClass;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return BeanExtUtils.convert(authentication.getPrincipal(), UserVO::new);
        }
        return null;
    }

}
