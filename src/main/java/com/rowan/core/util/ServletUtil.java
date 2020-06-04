package com.rowan.core.util;

import com.rowan.core.web.RequestContext;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

/**
 * servlet工具包
 *
 * @author zhanghao
 * @date 2019/8/28 18:47
 **/
public class ServletUtil {

    public static boolean isAjaxRequest() {
        return isAjaxRequest(RequestContext.getRequest());
    }

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String requestType = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equalsIgnoreCase(requestType);
    }

    public static Map<String, String>[] getClientInfoStat(HttpServletRequest request) {
        Map<String, String>[] infoMapArr = new Map[3];
        Map<String, String> headInfoMap = getHeaderInfo(request);
        Map<String, String> cookieInfoMap = getCookieInfo(request);
        Map<String, String> infoMap = getClientRequestInfo(request);
        infoMapArr[0] = headInfoMap;
        infoMapArr[1] = infoMap;
        infoMapArr[2] = cookieInfoMap;
        return infoMapArr;
    }

    private static Map<String, String> getHeaderInfo(HttpServletRequest request) {
        Map<String, String> headInfoMap = new TreeMap();
        Enumeration names = request.getHeaderNames();

        while(names != null & names.hasMoreElements()) {
            String name = (String)names.nextElement();
            headInfoMap.put("head:[" + name + "]", request.getHeader(name));
        }

        return headInfoMap;
    }

    public static Map<String, String> getCookieInfo() {
        return getCookieInfo(RequestContext.getRequest());
    }

    private static Map<String, String> getCookieInfo(HttpServletRequest request) {
        Map<String, String> cookieInfoMap = new TreeMap();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Cookie[] var3 = cookies;
            int var4 = cookies.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                Cookie cookie = var3[var5];
                cookieInfoMap.put("cookie:[" + cookie.getName() + "]", cookie.getValue());
            }
        }

        return cookieInfoMap;
    }

    private static Map<String, String> getClientRequestInfo(HttpServletRequest request) {
        Map<String, String> infoMap = new TreeMap();
        String getMethod = request.getMethod();
        infoMap.put("getMethod", getMethod);
        String getRequestURI = request.getRequestURI();
        infoMap.put("getRequestURI", getRequestURI);
        String getServletPath = request.getServletPath();
        infoMap.put("getServletPath", getServletPath);
        String getServerName = request.getServerName();
        infoMap.put("getServerName", getServerName);
        int getServerPort = request.getServerPort();
        infoMap.put("getServerPort", getServerPort + "");
        String getRemoteAddr = request.getRemoteAddr();
        infoMap.put("getRemoteAddr", getRemoteAddr);
        String getRemoteHost = request.getRemoteHost();
        infoMap.put("getRemoteHost", getRemoteHost);
        String getProtocol = request.getProtocol();
        infoMap.put("getProtocol", getProtocol);
        String getCharacterEncoding = request.getCharacterEncoding();
        infoMap.put("getCharacterEncoding", getCharacterEncoding);
        int getContentLength = request.getContentLength();
        infoMap.put("getContentLength", getContentLength + "");
        String getContentType = request.getContentType();
        infoMap.put("getContentType", getContentType);
        String getContextPath = request.getContextPath();
        infoMap.put("getContextPath", getContextPath);
        String getLocalAddr = request.getLocalAddr();
        infoMap.put("getLocalAddr", getLocalAddr);
        String getLocalName = request.getLocalName();
        infoMap.put("getLocalName", getLocalName);
        int getLocalPort = request.getLocalPort();
        infoMap.put("getLocalPort", getLocalPort + "");
        String getPathInfo = request.getPathInfo();
        infoMap.put("getPathInfo", getPathInfo);
        String getPathTranslated = request.getPathTranslated();
        infoMap.put("getPathTranslated", getPathTranslated);
        String getQueryString = request.getQueryString();
        infoMap.put("getQueryString", getQueryString);
        int getRemotePort = request.getRemotePort();
        infoMap.put("getRemotePort", getRemotePort + "");
        String getRemoteUser = request.getRemoteUser();
        infoMap.put("getRemoteUser", getRemoteUser);
        String getRequestedSessionId = request.getRequestedSessionId();
        infoMap.put("getRequestedSessionId", getRequestedSessionId);
        StringBuffer getRequestURL = request.getRequestURL();
        infoMap.put("getRequestURL", getRequestURL.toString());
        String getScheme = request.getScheme();
        infoMap.put("getScheme", getScheme);
        ServletContext getServletContext = request.getServletContext();
        infoMap.put("getServletContext.getContextPath", getServletContext.getContextPath());
        HttpSession getSession = request.getSession();
        infoMap.put("getSession.getId", getSession.getId());
        Principal getUserPrincipal = request.getUserPrincipal();
        if (null != getUserPrincipal) {
            infoMap.put("getUserPrincipal", getUserPrincipal.getName());
        }

        infoMap.put("getLastPath", getLastPath(request));
        infoMap.put("getFullUrl", getFullUrl(request));
        infoMap.put("getIp", getIp(request));
        return infoMap;
    }

    public static String getLastPath() {
        return getLastPath(RequestContext.getRequest());
    }

    public static String getLastPath(HttpServletRequest request) {
        String lastPath = request.getHeader("Referer");
        request.setAttribute("lastPath", lastPath);
        return lastPath;
    }

    public static String getFullUrl() {
        return getFullUrl(RequestContext.getRequest());
    }

    public static String getFullUrl(boolean proxyServer) {
        return getFullUrl(RequestContext.getRequest(), proxyServer);
    }

    public static String getFullUrl(HttpServletRequest request) {
        String uri = request.getRequestURL().toString();
        String url = request.getHeader("fullurl");
        if (StringUtil.isEmpty(url)) {
            String actualFullUrl = getFullUrl(request, false);
            return actualFullUrl;
        } else {
            url = url.replace("amp;", "");
            //log.debug("get fullurl: " + url);
            return url;
        }
    }

    public static String getFullUrl(HttpServletRequest request, boolean proxyServer) {
        String queryString = getParameterString(request);
        String url = request.getRequestURL().toString();
        if (StringUtil.isNotEmpty(queryString)) {
            url = url + "?" + queryString;
        }

        if (!proxyServer) {
            url = url.replaceFirst(request.getContextPath(), "");
        }

        if (url.endsWith("&")) {
            url = url.substring(0, url.length() - 1);
        }

        return url;
    }

    public static String getParameterString() {
        return getParameterString(RequestContext.getRequest());
    }

    public static String getParameterString(HttpServletRequest request) {
        Map<?, ?> paramMap = getParameters(request);
        return getParameterString(paramMap);
    }

    public static String getParameterString(Map<?, ?> ps, String... excludedKey) {
        return UrlEncryptUtil.getParameterString(ps, excludedKey);
    }

    public static Map<?, ?> getParameters() {
        return getParameters(RequestContext.getRequest());
    }

    public static Map<?, ?> getParameters(HttpServletRequest request) {
        return request.getParameterMap();
    }

    public static String getIp() {
        return getIp(RequestContext.getRequest());
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        return ip;
    }
}
