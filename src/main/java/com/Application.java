package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 启动类
 * @author zhangHao
 * @date 2019/7/15 22:47
*/
@SpringBootApplication
@ComponentScan({"com.clare"})
//注意这里的包是tk.mybatis.spring.annotation.MapperScan;
@MapperScan({"com.clare.mapper"})
@ServletComponentScan({"com.clare.controller.api"})
@EnableCaching
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
