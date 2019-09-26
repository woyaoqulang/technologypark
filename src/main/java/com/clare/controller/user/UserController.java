package com.clare.controller.user;


import com.clare.core.common.BaseController;
import com.clare.core.config.ConfigProperties;
import com.clare.core.model.ResultApi;
import com.clare.po.User;
import com.clare.service.UserService;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
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
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation("获取用户登陆列表")
    @GetMapping("/getUserInfoList")
    public ResultApi getUserInfoList(){
        PageHelper.startPage(1,10);
        List<User> result =userService.getUserInfoList();
        ResultApi<List<User>> objectResultApi = new ResultApi<>();
        objectResultApi.setResult(result);
        return objectResultApi;
    }


}
