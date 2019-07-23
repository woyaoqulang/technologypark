package com.clare.controller.user;


import com.clare.mapper.UserMapper;
import com.clare.po.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 * @author zhangHao
 * @date 2019/7/15 23:14
*/
@Api(tags = "用户")
@RestController
@RequestMapping("/technology")
@CommonsLog
public class UserController {


    @ApiOperation("")
    @GetMapping("/getUser")
    public User getUser(){
        User user = new User();

        return user;
    }
}
