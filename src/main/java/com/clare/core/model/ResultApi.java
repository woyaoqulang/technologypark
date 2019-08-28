package com.clare.core.model;

import com.clare.core.common.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
/**
 * 统一返回结果对象
 * @author zhanghao
 * @date 2019/8/28 17:05
**/
@ApiModel("返回的统一结果")
public class ResultApi<T> extends Object implements Serializable {

    private static final long serialVersionUID = 830054763344341448L;

    @ApiModelProperty("错误码,几个固定值为: 0:成功,400:参数不合法,401:未认证，未登录，一般需要跳转到登录界面要求登录,403:已登录，但是无权限，拒绝访问,404:资源找不到,500:错误提示。600:接口请求正常，但是有来自接口的业务提示")
    private String errorCode;
    @ApiModelProperty("对应错误消息描述，适合单个错误消息")
    private Object errorMessage;
    @ApiModelProperty("对应错误消息描述，适合多个错误消息")
    private List<Object> errorMessageList;
    @ApiModelProperty("返回的结果集")
    private T result;

    public ResultApi() {
    }

    public ResultApi(String errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = ErrorCode.errorMap.get(errorCode);
    }

    public ResultApi(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ResultApi(String errorCode, List<Object> errorMessageList) {
        this.errorCode = errorCode;
        this.errorMessageList = errorMessageList;
    }


    public ResultApi(String errorCode, String errorMessage, T result) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.result = result;
    }


    public ResultApi(String errorCode, List<Object> errorMessageList, T result) {
        this.errorCode = errorCode;
        this.errorMessageList = errorMessageList;
        this.result = result;
    }


    public ResultApi(String errorCode, T result) {
        this.errorCode = errorCode;
        this.result = result;
    }

    public String getErrorCode() {
        return this.errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        //this.errorMessage = ErrorCode.errorMap.get(errorCode);
    }

    public Object getErrorMessage() {
        return this.errorMessage;
    }
    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<Object> getErrorMessageList() {
        return this.errorMessageList;
    }
    public void setErrorMessageList(List<Object> errorMessageList) {
        this.errorMessageList = errorMessageList;
    }

    public T getResult() {
        return this.result;
    }
    public void setResult(T result) {
        this.result = result;
    }






}
