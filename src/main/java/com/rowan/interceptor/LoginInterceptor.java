package com.rowan.interceptor;

import com.alibaba.fastjson.JSON;
import com.rowan.constants.CookieConstant;
import com.rowan.core.common.ResultApi;
import com.rowan.core.util.CookieUtils;
import com.rowan.core.util.ServletUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 拦截器
 *
 * @author zhangHao
 * @date 2019-07-19 16:08
 */
@Component
@CommonsLog
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("\n登录拦截器开始");
        String loginToken = CookieUtils.getCookieValue(request, CookieConstant.LOGIN);
        log.info("loginToken:" + loginToken);
        if (StringUtils.isBlank(loginToken)) {
            if (ServletUtil.isAjaxRequest(request)) {
                sendAjaxMsg(response);
            } else {
                response.sendRedirect("/technology/index");
            }
            return false;
        }
        return true;
    }


    /**
     * @description ajax请求返回数据
     * @author zouzhiyong
     * @date 2019/5/10 19:54
     */
    public void sendAjaxMsg(HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            PrintWriter out = response.getWriter();
            // 未登录，返回401，未登录认证
            ResultApi<Object> result = ResultApi.build(400, "用户未登录");
            out.write(JSON.toJSONString(result));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
