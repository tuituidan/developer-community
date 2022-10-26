package com.tuituidan.openhub.config.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * LoginSuccessHandler.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/26
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        String returnUrl = (String) request.getSession().getAttribute("returnUrl");
        response.sendRedirect(returnUrl);
    }

}
