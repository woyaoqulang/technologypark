package com.rowan.core.dao;

import java.lang.annotation.*;

/**
 * 动态数据源注解
 * @author zhanghao
 * @date 2019/9/30 14:50
**/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface DataSource {
    String value();
}
