package com.rowan.core.web;

import com.rowan.core.model.PageInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring请求上下文
 *
 * @author zhanghao
 * @date 2019/9/26 18:05
 **/
public class RequestContext {

    private static final ThreadLocal<RequestContext> contextThreadLocal = new ThreadLocal<>();
    private HttpSession session;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private RedirectAttributes redirectAttributes;
    private Map<String, Cookie> cookies;
    private Model model;
    private PageInfo pageInfo;
    private Pageable pageable;
    private Object handler;

    /**
     * 初始化上下文
     *
     * @return
     * @author zhanghao
     * @date 2019/8/30 14:23
     **/
    public static RequestContext start(HttpServletRequest request, HttpServletResponse response) {

        RequestContext requestContext = new RequestContext();
        requestContext.request = request;
        requestContext.response = response;
        requestContext.session = request.getSession();
        requestContext.cookies = new HashMap<>(16);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie ck : cookies) {
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
     *
     * @return
     */
    public static RequestContext get() {
        return contextThreadLocal.get();
    }

    public static Model getModel() {
        RequestContext requestContext = get();
        return requestContext == null ? null : requestContext._getModel();
    }

    public void setModel(Model model) {
        RequestContext requestContext = contextThreadLocal.get();
        if (requestContext != null) {
            requestContext._setModel(model);
            contextThreadLocal.set(requestContext);
        }
    }

    public static HttpServletResponse getResponse() {
        RequestContext requestContext = get();
        return requestContext == null ? null : requestContext._getResponse();
    }

    public static <T> PageInfo<T> getPageInfo() {
        RequestContext requestContext = contextThreadLocal.get();
        return requestContext == null ? null : requestContext._getPageInfo();
    }

    public void setPageInfo(PageInfo<?> pageInfo) {
        RequestContext requestContext = contextThreadLocal.get();
        if (requestContext != null) {
            requestContext._setPageInfo(pageInfo);
            requestContext._setPageable(PageRequest.of(pageInfo.getPageNo(), pageInfo.getPageSize()));
            contextThreadLocal.set(requestContext);
        }
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
        this.pageInfo = null;
        this.pageable = null;
        this.handler = null;
        contextThreadLocal.remove();
    }

    private Model _getModel() {
        return this.model;
    }

    private HttpServletResponse _getResponse() {
        return this.response;
    }

    private void _setModel(Model model) {
        this.model = model;
    }

    private <T> PageInfo<T> _getPageInfo() {
        return this.pageInfo;
    }

    private void _setPageInfo(PageInfo<?> pageInfo) {
        this.pageInfo = pageInfo;
    }

    private void _setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
