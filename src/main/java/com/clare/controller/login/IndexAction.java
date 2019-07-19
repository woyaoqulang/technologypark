package com.clare.controller.login;


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

    @RequestMapping("/loginPage")
    public String index(){
        return "login";
    }
}
