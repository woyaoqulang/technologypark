package com.rowan.core.common;

import com.rowan.core.constant.HttpStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 统一返回结果对象
 *
 * @author zhanghao
 * @date 2019/8/28 17:05
 **/
@ApiModel("返回的统一结果")
public class ResultApi<T> implements Serializable {

    private static final long serialVersionUID = 830054763344341448L;

    @ApiModelProperty("状态码,几个固定值为: 200:成功,400:参数不合法,401:未认证，未登录，一般需要跳转到登录界面要求登录,403:已登录，但是无权限，拒绝访问,404:资源找不到,500:错误提示。600:接口请求正常，但是有来自接口的业务提示")
    private Integer code;
    @ApiModelProperty("对应错误消息描述，适合单个错误消息")
    private Object message;
    @ApiModelProperty("对应错误消息描述，适合多个错误消息")
    private List<Object> messageList;
    @ApiModelProperty("返回的结果集")
    private T result;

    public static <T> ResultApi<T> ok(T result) {
        ResultApi<T> resultApi = new ResultApi();
        resultApi.setCode(200);
        resultApi.setResult(result);
        resultApi.setMessage(HttpStatusEnum.getMessage(200));
        return resultApi;
    }

    public static <T> ResultApi<T> ok() {
        ResultApi<T> resultApi = new ResultApi();
        resultApi.setCode(200);
        resultApi.setMessage(HttpStatusEnum.getMessage(200));
        return resultApi;
    }

    public static <T> ResultApi<T> build500(String message) {
        ResultApi<T> resultApi = new ResultApi();
        resultApi.setCode(500);
        resultApi.setMessage(message);
        return resultApi;
    }

    public static <T> ResultApi<T> build(Integer statusCode, String message) {
        ResultApi<T> resultApi = new ResultApi();
        resultApi.setCode(statusCode);
        resultApi.setMessage(message);
        return resultApi;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public List<Object> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Object> messageList) {
        this.messageList = messageList;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
