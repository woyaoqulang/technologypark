package com.clare.config;


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
 * 接口配置
 * @author zhangHao
 * @date 2019/7/15 22:46
*/
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket userApi(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        docket.apiInfo(apiInfo())
                .groupName("用户信息")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.clare.controller.user"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Technology")
                .description("技术讨论社区")
                .termsOfServiceUrl("http://technologypark.com/technology/loginPage")
                .contact(new Contact("张浩","+86 17821832110","z1679619459@163.com"))
                .version("1.0")
                .build();
    }
}
