package com.clare.core.exception;

import lombok.extern.apachecommons.CommonsLog;

/**
 *
 * @author zhanghao
 * @date 2019/9/26 17:39
**/
@CommonsLog
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
