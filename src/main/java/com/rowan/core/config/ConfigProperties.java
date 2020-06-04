package com.rowan.core.config;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 读取配置文件
 * @author zhanghao
 * @date 2019/8/28 14:48
**/
@Configuration
@PropertySource(value = "classpath:config.properties",encoding = "UTF-8")
public class ConfigProperties {

    @Value("${token_name}")
    @ApiModelProperty(value="登录token-key值")
    private String tokenName;

    @Value("${token_expire}")
    @ApiModelProperty(value="登录token失效时间")
    private Integer tokenExpire;

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public Integer getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(Integer tokenExpire) {
        this.tokenExpire = tokenExpire;
    }
}
