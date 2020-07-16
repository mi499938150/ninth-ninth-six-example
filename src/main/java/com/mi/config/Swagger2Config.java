package com.mi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : Rong
 * @date : 2020/7/16
 * @Desc: Swagger2 配置类
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    /**
     *  Swagger2信息
     * @return
     */
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                // API 基本信息
                .apiInfo(apiInfo())
                // 设置允许暴露的接口
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.mi.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("996-练习")
                .description("整合知识点")
                .contact(new Contact("xiaoxx","",""))
                .version("1.0.0")
                .build();
    }
}