package com.rowan.interceptor;

import com.rowan.core.web.RequestContext;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 系统拦截器
 *
 * @author zhanghao
 * @date 2019/8/30 14:28
 **/
@Component
@CommonsLog
public class SystemInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("\n系统拦截器开始");
        RequestContext.start(request, response);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        RequestContext requestContext = RequestContext.get();
        if (null != requestContext) {
            RequestContext.get().clearContext();
        }

    }


}


