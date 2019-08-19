package com.clare.core.util;

import com.clare.core.web.RequestContext;

import javax.servlet.http.HttpServletRequest;

public class ServletUtil {

    public static boolean isAjaxRequest() {
        return isAjaxRequest(RequestContext.getRequest());
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String feign = request.getHeader("feign");
        if (StringUtil.isNotEmptyString(feign)) {
            return false;
        } else {
            String requestType = request.getHeader("X-Requested-With");
            String accessToken = request.getHeader("x-access-token");
            String isAjax = request.getHeader("isAjax");
            if (StringUtil.isEmptyString(isAjax)) {
                isAjax = (String)request.getAttribute("isAjax");
            }

            return StringUtil.isNotEmpty(accessToken) || "XMLHttpRequest".equalsIgnoreCase(requestType) || "true".equalsIgnoreCase(isAjax);
        }
    }
}
