package com.clare.core.common;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:config.properties",encoding = "UTF-8")
@Data
public class ConfigProperties {


    @Value("${token_name}")
    @ApiModelProperty(value="登录token-key值")
    private String tokenName;

    @Value("${token_expire}")
    @ApiModelProperty(value="登录token失效时间")
    private Integer tokenExpire;

}
