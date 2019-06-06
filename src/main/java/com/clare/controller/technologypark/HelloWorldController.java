package com.clare.controller.technologypark;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 世界你好
 * @author: ZhangHao
 * @create: 2019-04-26 15:48
 **/
@RestController
public class HelloWorldController
{
    @GetMapping("/hello")
    public String say(){
        return "Hello woddrld, ffs";
    }
}
