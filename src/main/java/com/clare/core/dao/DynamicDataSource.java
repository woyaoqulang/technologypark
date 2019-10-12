package com.clare.core.dao;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 数据源
 * @author zhanghao
 * @date 2019/10/10 14:59
**/
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 获取数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
