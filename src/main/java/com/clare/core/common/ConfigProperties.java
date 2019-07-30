package com.clare.core.common;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "config.properties")
@Data
public class ConfigProperties {

    @Value("${login}")
    private String tokenName;


}
