package com.clare;


import com.clare.core.config.ConfigProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigPropertiesTest {

    @Autowired
    private ConfigProperties configProperties;

    @Test
    public void test1(){
        System.out.println(configProperties.getTokenName());
        System.out.println(configProperties.getTokenExpire());
    }


}
