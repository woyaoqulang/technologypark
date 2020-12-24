package com.rowan.core.dao;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源配置
 *
 * @author zhanghao
 * @date 2019/9/30 18:31
 **/
@Configuration
public class DynamicDataSourceConfig implements EnvironmentAware {

    private Logger log = LoggerFactory.getLogger(DynamicDataSourceConfig.class);

    /**
     * 默认数据源
     */
    private static final String DEFAULT_NAME = "spring.custom.datasource.default-name";
    /**
     * 多数据源名称
     */
    private static final String DATA_SOURCE_NAME = "spring.custom.datasource.name";
    /**
     * 数据源前缀
     */
    private static final String DATA_SOURCE_PREFIX = "spring.custom.datasource";
    /**
     * druid配置前缀
     */
    private static final String DRUID_PREFIX = "spring.datasource.druid";

    private Map<String, DruidDataSource> customDataSource = new HashMap<>();

    private Environment environment;

    /**
     * 获取默认数据源
     *
     * @return
     */
    public DataSource getDefaultDataSource() {
        return getDataSource(environment.getProperty(DEFAULT_NAME));
    }

    /**
     * 根据定义的name获取指定数据源
     *
     * @param name
     * @return
     */
    public DataSource getDataSource(String name) {
        Assert.notNull(this.customDataSource, "没有配置自定义数据源，不需要获取动态数据源");
        DruidDataSource druidDataSource = customDataSource.get(name);
        Assert.notNull(druidDataSource, "配置了数据源名称【" + DEFAULT_NAME + "】，但是没有找到该数据源，请检查配置");
        return druidDataSource;
    }


    @Bean(name = {"dynamicDataSource"})
    @Primary
    public DynamicDataSource dynamicDataSource() {
        Map<Object, Object> targetDataSource = new HashMap<>(10);
        initCustomDataSource();
        targetDataSource.putAll(customDataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSource);
        dynamicDataSource.setDefaultTargetDataSource(getDefaultDataSource());
        dynamicDataSource.afterPropertiesSet();
        return dynamicDataSource;
    }

    private void initCustomDataSource() {
        //获取当前绑定
        Binder binder = Binder.get(environment);
        //默认数据源
        String defaultName = environment.getProperty(DEFAULT_NAME);
        //所有数据源
        String dataSourceNames = environment.getProperty(DATA_SOURCE_NAME);
        if (StringUtils.isEmpty(dataSourceNames)) {
            log.error("多个数据源列表为空");
        } else {
            String[] split = dataSourceNames.split(",");
            for (String s : split) {
                String key = DATA_SOURCE_PREFIX + "." + s;
                DruidDataSource dataSource = binder.bind(key, Bindable.of(DruidDataSource.class)).get();
                dataSource = binder.bind(DRUID_PREFIX, Bindable.ofInstance(dataSource)).get();
                customDataSource.put(s, dataSource);
            }
        }
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
