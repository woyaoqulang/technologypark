package com.clare.controller.technologypark;

import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: 登录
 * @author: ZhangHao
 * @create: 2019-04-26 15:48
 **/
@Controller
public class HelloWorldController
{
    @RequestMapping("/index")
    public String index(){
        return "login";
    }
}
