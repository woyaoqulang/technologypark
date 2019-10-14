package com.clare.core.dao;

import com.alibaba.druid.pool.DruidDataSource;
import com.clare.core.util.StringUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@CommonsLog
public class DynamicDataSourceConfig implements EnvironmentAware {

    private static final String DEFAULT_NAME="spring.custom.datasource.default-name";

    private static final String DATA_SOURCE_NAME="spring.custom.datasource.name";

    private Map<String, DruidDataSource> customDataSource;

    private Environment environment;

    /**
     * 获取默认数据源
     * @return
     */
    public DataSource getDefaultDataSource(){
        return getDataSource(environment.getProperty(DEFAULT_NAME));
    }

    /**
     * 根据定义的name获取指定数据源
     * @param name
     * @return
     */
    public DataSource getDataSource(String name){
        Assert.notNull(this.customDataSource, "没有配置自定义数据源，不需要获取动态数据源");
        DruidDataSource druidDataSource = customDataSource.get(name);
        Assert.notNull(druidDataSource, "配置了数据源名称【" + DEFAULT_NAME + "】，但是没有找到该数据源，请检查配置");
        return druidDataSource;
    }

    @Bean(name = {"dynamicDataSource"})
    @Primary
    public String dynamicDataSource(){

        Binder binder = Binder.get(environment);

        //默认数据源
        String defaultName = environment.getProperty(DEFAULT_NAME);
        //所有数据源
        String dataSourceNames = environment.getProperty(DATA_SOURCE_NAME);
        if(StringUtil.isEmpty(dataSourceNames)){
            log.error("多个数据源列表为空");
        }else{
            String[] split = dataSourceNames.split(",");
            //environment.getRequiredProperty()
        }

        return defaultName;
    }


    /*@Bean(name = {"dynamicDataSource"})
    @Primary
    public DynamicDataSource dynamicDataSource(){
        HashMap<Object, Object> targetDataSource = new HashMap<>();
        initCustomDataSource(targetDataSource);
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSource);
        dynamicDataSource.setDefaultTargetDataSource(getDefaultDataSource());
        dynamicDataSource.afterPropertiesSet();
        return dynamicDataSource;
    }*/

    private void initCustomDataSource(HashMap<Object, Object> targetDataSource) {
        //String[] activeProfiles = environment.getActiveProfiles();
        //System.out.println(activeProfiles);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment=environment;
    }
}
