package com.rowan.core.lifecycle;

import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

/**
 * @description: springBoot启动完成
 * @author: rowan
 * @date: 2020-06-28 11:08
 **/
@Component
public class DefaultSmartLifecycle implements SmartLifecycle {

    @Override
    public void start() {
        System.out.println("spring boot启动后业务初始化");
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
