package com.clare.controller.index;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登录页面
 * @author zhangHao
 * @date 2019-07-19 15:40
 */
@Controller
@RequestMapping("/")
public class IndexAction {

    /**
     * 登陆页
     * @author zhanghao
     * @date 2019/8/16 15:45
     * @return
    **/
    @RequestMapping({"/","/technology/index"})
    public String lteLogin(){
        return "/index/adminIndex";
    }
    
    /**
     * 注册
     * @author zhanghao
     * @date 2019/8/16 16:30
     * @return 
    **/        
    @RequestMapping("/technology/register")
    public String register(){
        return "/index/register";
    }


    @RequestMapping("/layui")
    public String layuiLogin(){
        return "index";
    }



}
