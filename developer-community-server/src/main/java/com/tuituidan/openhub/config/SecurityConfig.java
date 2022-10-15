package com.tuituidan.openhub.config;

/**
 * SecurityConfig.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/9/25
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfig {
    /**
     * filterChain
     *
     * @param http http
     * @return SecurityFilterChain
     * @throws Exception Exception
     */
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        return http
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/api/v1/login")
//                .and().logout()
//                .logoutUrl("/logout").logoutSuccessUrl("/admin/category")
//                .deleteCookies("JSESSIONID")
//                .and().authorizeRequests()
//                .antMatchers("/api/v1/card/tree", "/api/v1/qrcode").permitAll()
//                .and().authorizeRequests()
//                .antMatchers("/admin/**", "/api/v1/**").authenticated()
//                .anyRequest().permitAll()
//                .and().headers().frameOptions().disable()
//                .and().csrf().disable()
//                .exceptionHandling()
//                .defaultAuthenticationEntryPointFor((request, response, ex) ->
//                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED),
//                        request -> "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With")))
//                .and().build();
//    }
}
