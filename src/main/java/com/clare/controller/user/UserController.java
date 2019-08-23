package com.clare.controller.user;


import com.clare.core.model.ResultApi;
import com.clare.mapper.UserMapper;
import com.clare.po.User;
import com.clare.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户登陆列表")
    @GetMapping("/getUserInfoList")
    public ResultApi getUserInfoList(){

        List<User> result =userService.getUserInfoList();
        ResultApi<List<User>> objectResultApi = new ResultApi<>();
        objectResultApi.setResult(result);
        return objectResultApi;
    }


}
