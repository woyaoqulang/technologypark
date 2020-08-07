package com.rowan.core.dao;

import com.rowan.core.util.AopUtil;
import lombok.extern.apachecommons.CommonsLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 动态数据源切面
 *
 * @author zhanghao
 * @date 2019/10/10 15:49
 **/
@Aspect
@Component
@ConditionalOnProperty({"spring.custom.datasource.default-name"})
@CommonsLog
public class DynamicDataSourceAop implements Ordered {

    @Pointcut("execution(* com..*.service..*.*(..))")
    public void checkParam() {
    }

    @Before("checkParam()")
    public void doBefore(JoinPoint joinpoint) {

    }

    @Around("checkParam()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Class<?> aClass = pjp.getTarget().getClass();
        DataSource typeAnnotation = aClass.getAnnotation(DataSource.class);
        Method method = AopUtil.getSpecificMethod(pjp);
        DataSource methodAnnotation = method.getAnnotation(DataSource.class);
        String dataSourceName = null;
        String methodName = aClass.getName() + "." + method.getName();
        if (null == methodAnnotation && null == typeAnnotation) {
            return pjp.proceed();
        }
        if (null != methodAnnotation) {
            dataSourceName = methodAnnotation.value();
        } else {
            dataSourceName = typeAnnotation.value();
        }
        log.info(methodName + ",选择数据源" + dataSourceName);
        DynamicDataSourceContextHolder.setDataSourceType(dataSourceName);
        Object result = null;
        try {
            result = pjp.proceed();
            return result;
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceType();
        }
    }


    @AfterReturning("checkParam()")
    public void doAfterReturning(JoinPoint joinPoint) {
    }

    @Override
    public int getOrder() {
        return 10;
    }
}
