package com.clare.core.dao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 动态数据源配置
 * @author zhanghao
 * @date 2019/9/30 18:31
**/

@Configuration
@ConfigurationProperties(value = "app")
public class DataSourceConfig {


}
