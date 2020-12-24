package com.rowan.core.model;

/**
 * 返回数据对象工厂
 *
 * @author zhanghao
 * @date 2019/8/28 16:15
 **/
public class ResultApiBuilder {

    public static <T> ResultApi<T> buildResultApi(T result) {
        ResultApi<T> resultApi = new ResultApi();
        resultApi.setErrorCode("0");
        resultApi.setResult(result);
        return resultApi;
    }

    public static <T> ResultApi<T> buildResultApi(String errorCode) {
        ResultApi<T> resultApi = new ResultApi();
        resultApi.setErrorCode(errorCode);
        return resultApi;
    }

    public static <T> ResultApi<T> buildResultApi(String errorCode, Object message) {
        ResultApi<T> resultApi = new ResultApi();
        resultApi.setErrorCode(errorCode);
        resultApi.setErrorMessage(message);
        return resultApi;
    }

    public static <T> ResultApi<T> buildResultApi(String errorCode, T result, Object message) {
        ResultApi<T> resultApi = new ResultApi();
        resultApi.setErrorCode(errorCode);
        resultApi.setResult(result);
        resultApi.setErrorMessage(message);
        return resultApi;
    }


}
