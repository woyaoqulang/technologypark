package com.clare.controller.login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录页面
 * @author zhangHao
 * @date 2019-07-19 15:40
 */
@Controller
@RequestMapping("/technolog")
public class IndexAction {

    @Autowired
    RedisTemplate<String,String> redisTemplate;


    @RequestMapping("/loginPage")
    public String index(){
        return "login";
    }

    @RequestMapping("/user")
    public String user(){
        System.out.println(redisTemplate.opsForValue().get("name"));
        return "user";
    }


}
