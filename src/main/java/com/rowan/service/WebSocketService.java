package com.rowan.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.rowan.core.common.ResultApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @description: websocket推送服务
 * @author: zhanghao
 * @date: 2021-1-26 19:24:7
 **/
@Service
@Slf4j
public class WebSocketService {

    @Autowired
    SimpMessageSendingOperations simpMessageSendingOperations;

    @Async
    public void pushTest() {
        ResultApi<String> ok = ResultApi.ok("");
        String jsonInfo = JSONObject.toJSONString(ok, SerializerFeature.WriteDateUseDateFormat, SerializerFeature.DisableCircularReferenceDetect);
        log.info("推送运行任务开始：{}", jsonInfo);
        String topic = "";
        simpMessageSendingOperations.convertAndSend(topic, jsonInfo);
    }


}
