package com.rowan.controller.api;

import com.rowan.core.common.ResultApi;
import com.rowan.service.WebSocketService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 测试api
 * @author: zhanghao
 * @date: 2021-2-1 16:49:52
 **/
@Api(tags = "测试类")
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    WebSocketService webSocketService;

    @ApiOperation("websocket推送测试")
    @GetMapping("/push")
    public ResultApi<String> push() {
        webSocketService.pushTest();
        return ResultApi.ok();
    }

    @ApiOperation("websocket单点用户推送测试")
    @GetMapping("/pushUser")
    public ResultApi<String> pushUser() {
        webSocketService.pushUserTest();
        return ResultApi.ok();
    }
}
