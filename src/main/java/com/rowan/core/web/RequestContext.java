package com.rowan.core.web;

import com.rowan.core.common.PageInfo;
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

    private static final ThreadLocal<RequestContext> CONTEXT_THREAD_LOCAL = new ThreadLocal<>();
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
        CONTEXT_THREAD_LOCAL.set(requestContext);
        return requestContext;
    }

    public static HttpServletRequest getRequest() {
        RequestContext requestContext = get();
        return requestContext.getCurrentRequest();
    }

    /**
     * 获取当前请求的上下文
     *
     * @return
     */
    public static RequestContext get() {
        return CONTEXT_THREAD_LOCAL.get();
    }

    public static Model getModel() {
        RequestContext requestContext = get();
        return requestContext == null ? null : requestContext.getCurrentModel();
    }

    public void setModel(Model model) {
        RequestContext requestContext = CONTEXT_THREAD_LOCAL.get();
        if (requestContext != null) {
            requestContext.setCurrentModel(model);
            CONTEXT_THREAD_LOCAL.set(requestContext);
        }
    }

    public static HttpServletResponse getResponse() {
        RequestContext requestContext = get();
        return requestContext == null ? null : requestContext.getCurrentResponse();
    }

    public static <T> PageInfo<T> getPageInfo() {
        RequestContext requestContext = CONTEXT_THREAD_LOCAL.get();
        return requestContext == null ? null : requestContext.getCurrentPageInfo();
    }

    public void setPageInfo(PageInfo<?> pageInfo) {
        RequestContext requestContext = CONTEXT_THREAD_LOCAL.get();
        if (requestContext != null) {
            requestContext.setCurrentPageInfo(pageInfo);
            requestContext.setCurrentPageable(PageRequest.of(pageInfo.getPageNo(), pageInfo.getPageSize()));
            CONTEXT_THREAD_LOCAL.set(requestContext);
        }
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
        CONTEXT_THREAD_LOCAL.remove();
    }

    private HttpServletRequest getCurrentRequest() {
        return request;
    }

    private Model getCurrentModel() {
        return this.model;
    }

    private HttpServletResponse getCurrentResponse() {
        return this.response;
    }

    private void setCurrentModel(Model model) {
        this.model = model;
    }

    private <T> PageInfo<T> getCurrentPageInfo() {
        return this.pageInfo;
    }

    private void setCurrentPageInfo(PageInfo<?> pageInfo) {
        this.pageInfo = pageInfo;
    }

    private void setCurrentPageable(Pageable pageable) {
        this.pageable = pageable;
    }

}
