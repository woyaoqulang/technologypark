package com.rowan.core.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * 处理数据源
 * @author zhanghao
 * @date 2019/10/10 14:54
**/
public class DynamicDataSourceContextHolder {

    /**
     * 使用ThreadLocal维护变量，ThreadLocal为每个使用该变量的线程提供的变量副本，
     * 每个线程都是独立改变自己的副本，而不会影响其他线程所对应的副本
     */
    private static final ThreadLocal<String> CONTEXT_HOLDER =new ThreadLocal<>();

    /**
     * 管理所有的数据源id,用于数据源的判断
     */
    public static List<String> contextHolderId = new ArrayList<>();

    public static void setDataSourceType(String dataSourceType){
        CONTEXT_HOLDER.set(dataSourceType);
    }

    public static String getDataSourceType(){
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceType(){
        CONTEXT_HOLDER.remove();
    }

}
