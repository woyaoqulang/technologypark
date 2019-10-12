package com.clare.core.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源配置
 * @author zhanghao
 * @date 2019/9/30 18:31
**/
@Configuration
@PropertySource(value = "classpath:config.properties",encoding = "UTF-8")
@ConditionalOnProperty({"spring.custom.datasource.default-name"})
public class DynamicDataSourceConfig {
    /**
     * 所有数据源集合
     */
    private Map<String, DruidDataSource> customDataSource;
    /**
     * 默认数据源
     */
    @Value("${spring.custom.datasource.default-name}")
    private String customDefaultDataSourceName;

    /**
     * 获取默认数据源
     * @return
     */
    public DataSource getDefaultDataSource(){
        return getDataSource(customDefaultDataSourceName);
    }

    /**
     * 根据定义的name获取指定数据源
     * @param name
     * @return
     */
    public DataSource getDataSource(String name){
        Assert.notNull(this.customDataSource, "没有配置自定义数据源，不需要获取动态数据源");
        DruidDataSource druidDataSource = customDataSource.get(name);
        Assert.notNull(druidDataSource, "配置了数据源名称【" + this.customDefaultDataSourceName + "】，但是没有找到该数据源，请检查配置");
        return druidDataSource;
    }

    @Bean(name = {"dynamicDataSource"})
    @Primary
    public DynamicDataSource dynamicDataSource(){
        HashMap<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.putAll(customDataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSource);
        dynamicDataSource.setDefaultTargetDataSource(getDefaultDataSource());
        dynamicDataSource.afterPropertiesSet();
        return dynamicDataSource;
    }

}
