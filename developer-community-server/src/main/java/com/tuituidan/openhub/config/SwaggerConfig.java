package com.tuituidan.openhub.config;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * SwaggerConfig.
 *
 * @author tuituidan
 * @version 1.0
 * @date 2022/10/6
 */
@Configuration
@EnableOpenApi
public class SwaggerConfig {
    /**
     * 通过配置控制是否开启.
     */
    @Value("${swagger.show:true}")
    private boolean swaggerShow;

    /**
     * Docket docket.
     *
     * @return the docket
     */
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .enable(swaggerShow)
                .groupName("API_1.0")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tuituidan.openhub.controller"))
                .paths(regex("/api/v1.*"))
                .build();
    }

    /**
     * ApiInfo.
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("tresdin-demo")
                .description("tresdin-demo api。")
                .contact(new Contact("推推蛋", "", "tuituidan@163.com"))
                .termsOfServiceUrl("https://gitee.com/tuituidan/tresdin-demo")
                .version("1.0.0")
                .build();
    }
}
