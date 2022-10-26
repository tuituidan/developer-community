package com.tuituidan.openhub.config.security;

import com.tuituidan.openhub.service.UserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SecurityConfig.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/9/25
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private UserService userService;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    /**
     * filterChain
     *
     * @param http http
     * @return SecurityFilterChain
     * @throws Exception Exception
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin().successHandler(loginSuccessHandler);
        http.userDetailsService(userService);
        http.authorizeRequests().antMatchers("/api/v1/login").permitAll()
                .antMatchers("/api/v1/**").authenticated();
        http.headers().frameOptions().disable();
        http.csrf().disable();
        http.exceptionHandling().defaultAuthenticationEntryPointFor((request, response, ex) ->
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED),
                request -> "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")));

        return http.build();
    }

    /**
     * bCryptPasswordEncoder
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder cryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
