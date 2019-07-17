package com.clare.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误码
 * @author zhangHao
 * @date 2019/7/16 22:58
*/
public class ErrorCode {
    public static final Map<String, String> errorMap = new HashMap();
    public static final String errorCodeKey = "errorCode";
    public static final String errorMessageKey = "errorMessage";
    public static final String code_0 = "0";
    public static final String code_1 = "1";
    public static final String code_301 = "301";
    public static final String code_400 = "400";
    public static final String code_401 = "401";
    public static final String code_403 = "403";
    public static final String code_404 = "404";
    public static final String code_500 = "500";
    public static final String code_600 = "600";

    public ErrorCode() {
    }

    static {
        errorMap.put("0", "成功");
        errorMap.put("1", "登录成功，但是未绑定");
        errorMap.put("301", "客户端重定向，结果中会带一个重定向的url,格式为httpMethod:url,如post:/update/233");
        errorMap.put("400", "参数错误");
        errorMap.put("401", "未认证，未登录");
        errorMap.put("403", "已登录，但是无权限，拒绝访问");
        errorMap.put("404", "找不到资源");
        errorMap.put("500", "服务端错误");
        errorMap.put("600", "接口请求正常，但是有来自接口的业务提示");
    }
}
