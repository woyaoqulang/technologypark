package com.rowan.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rowan.core.common.ResultApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @description: websocket推送服务
 * @author: zhanghao
 * @date: 2021-1-26 19:24:7
 **/
@Component
@Slf4j
public class WebSocketService {

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @Async
    public void pushTest() {
        ResultApi<String> ok = ResultApi.ok("噢噢噢噢");
        String jsonInfo = JSONObject.toJSONString(ok, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
        log.info("推送运行任务开始：{}", jsonInfo);
        String topic = "/topic/message";
        simpMessageSendingOperations.convertAndSend(topic, jsonInfo);
    }

    @Async
    public void pushUserTest() {
        ResultApi<String> ok = ResultApi.ok("噢噢噢噢");
        String jsonInfo = JSONObject.toJSONString(ok, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
        log.info("推送运行任务开始：{}", jsonInfo);
        String topic = "/message";
        simpMessageSendingOperations.convertAndSendToUser("1", topic, jsonInfo);
    }


}
