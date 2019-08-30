package com.clare.core.web;

import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class RequestContext {

    private static final ThreadLocal<RequestContext> contextThreadLocal = new ThreadLocal<RequestContext>();
    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RedirectAttributes redirectAttributes;
    private Map<String, Cookie> cookies;
    private Model model;
    private Pageable pageable;
    private Object handler;

    /**
     * 初始化上下文
     * @author zhanghao
     * @date 2019/8/30 14:23
     * @return
    **/
    public static RequestContext start(HttpServletRequest request, HttpServletResponse response)
    {

        RequestContext requestContext = new RequestContext();
        requestContext.request = request;
        requestContext.response = response;
        requestContext.session = request.getSession();
        requestContext.cookies = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (cookies != null)
        {
            for (Cookie ck : cookies)
            {
                requestContext.cookies.put(ck.getName(), ck);
            }
        }
        contextThreadLocal.set(requestContext);
        return requestContext;
    }

    public static HttpServletRequest getRequest() {
        RequestContext requestContext = get();
        return requestContext._getRequest();
    }

    /**
     * 获取当前请求的上下文
     * @return
     */
    public static RequestContext get() {
        return contextThreadLocal.get();
    }

    private HttpServletRequest _getRequest() {
        return request;
    }

    public void clearContext() {
        this.request = null;
        this.response = null;
        this.session = null;
        this.cookies = null;
        this.model = null;
        this.pageable = null;
        this.handler = null;
        contextThreadLocal.remove();
    }

    public static Model getModel() {
        RequestContext requestContext = get();
        return requestContext == null ? null : requestContext._getModel();
    }

    private Model _getModel() {
        return this.model;
    }

    public static HttpServletResponse getResponse() {
        RequestContext requestContext = get();
        return requestContext == null ? null : requestContext._getResponse();
    }

    private HttpServletResponse _getResponse() {
        return this.response;
    }

    public void setModel(Model model) {
        RequestContext requestContext = (RequestContext)contextThreadLocal.get();
        if (requestContext != null) {
            requestContext._setModel(model);
            contextThreadLocal.set(requestContext);
        }
    }

    private void _setModel(Model model) {
        this.model = model;
    }

}
