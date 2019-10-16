package com.ait.mobile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName Swagger2
 * @Author weizhengchen
 * @Email 1377252306@qq.com
 * @Date 2018/6/22 14:09
 * @Version 1.0.0
 **/
@Configuration
@EnableSwagger2
@Profile({"dev"})
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("mobile-api")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ait.mobile.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("人事系统手机端restful API ")
                .contact(new Contact("魏正晨","http://118.190.89.40:8080/","1377252306@qq.com"))
                .version("1.0")
                .build();
    }
}
