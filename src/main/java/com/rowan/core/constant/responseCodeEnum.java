package com.rowan.core.constant;

/**
 * @description: 返回错误码说明
 * @author: zhangHao
 * @date: 2020/12/24 17:23
 **/
public enum responseCodeEnum {

    //枚举定义
    STATUS_200(200, "成功"),
    STATUS_500(500, "接口内部错误"),
    STATUS_600(600, "业务提示消息"),
    STATUS_401(401, "未登录"),
    STATUS_400(400, "参数错误"),
    STATUS_404(404, "找不到资源");
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String message;

    responseCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
