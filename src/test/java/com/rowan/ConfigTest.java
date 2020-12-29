package com.rowan;

import com.alibaba.druid.pool.DruidDataSource;
import com.rowan.core.dao.RedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigTest {

    @Autowired
    private Environment environment;

    @Autowired
    private RedisDao redisDao;

    @Test
    public void test() {
        Binder binder = Binder.get(environment);
        DruidDataSource druidDataSource = binder.bind("spring.custom.datasource.technology", Bindable.of(DruidDataSource.class)).get();
        System.out.println(druidDataSource.getUrl());
        //druidDataSource = binder.bind("spring.datasource.druid", Bindable.ofInstance(druidDataSource)).get();
        System.out.println(druidDataSource.getMaxActive());
    }

    @Test
    public void redisTest() {
        redisDao.setForMinutes("username", "zhangsan", 30L);
        System.out.println(redisDao.get("username"));


    }
}
