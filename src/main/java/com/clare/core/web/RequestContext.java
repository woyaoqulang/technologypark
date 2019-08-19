package com.clare.core.web;

import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class RequestContext {

    private static final ThreadLocal<RequestContext> contextThreadLocal = new ThreadLocal();
    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RedirectAttributes redirectAttributes;
    private Map<String, Cookie> cookies;
    private Model model;
    private Pageable pageable;
    private Object handler;

    public RequestContext() {
    }
    public static HttpServletRequest getRequest() {
        RequestContext requestContext = get();
        return requestContext == null ? null : requestContext._getRequest();
    }

    public static RequestContext get() {
        return (RequestContext)contextThreadLocal.get();
    }

    private HttpServletRequest _getRequest() {
        return this.request;
    }
}
