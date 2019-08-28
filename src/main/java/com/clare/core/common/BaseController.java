package com.clare.core.common;

import com.clare.core.model.ResultApi;
import com.clare.core.model.ResultApiBuilder;
import com.clare.core.util.StringUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

/**
 * 公共controller
 * @author zhanghao
 * @date 2019/8/28 14:47
**/
public class BaseController {


    @ExceptionHandler({Exception.class})
    public ResultApi<String> exception(){
        
        return null;
    }
    protected <T> ResultApi<T> respond500(Object errorMessage) {
        return ResultApiBuilder.buildResultApi("500", errorMessage);
    }

    protected <T> ResultApi<T> respond500(T result, Object errorMessage) {
        return ResultApiBuilder.buildResultApi("500", result, errorMessage);
    }

    private boolean sendExceptionInfo(Exception exception) {
        String errorMessage = exception.getMessage();
        if (StringUtil.isNotEmpty(errorMessage) && errorMessage.contains("java.io.IOException: Broken pipe")) {
            return true;
        } else {
            //Map<String, String>[] requestInfoMap = StringUtil.getClientInfoStat(this.request());
            //this.sendExceptionInfo(requestInfoMap, exception);
            return false;
        }
    }


}
