package com.rowan.core.common;

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

    @ApiModelProperty("错误码,几个固定值为: 0:成功,400:参数不合法,401:未认证，未登录，一般需要跳转到登录界面要求登录,403:已登录，但是无权限，拒绝访问,404:资源找不到,500:错误提示。600:接口请求正常，但是有来自接口的业务提示")
    private String errorCode;
    @ApiModelProperty("对应错误消息描述，适合单个错误消息")
    private Object errorMessage;
    @ApiModelProperty("对应错误消息描述，适合多个错误消息")
    private List<Object> errorMessageList;
    @ApiModelProperty("返回的结果集")
    private T result;


    public static <T> ResultApi<T> ok() {
        return null;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Object> getErrorMessageList() {
        return errorMessageList;
    }

    public void setErrorMessageList(List<Object> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
