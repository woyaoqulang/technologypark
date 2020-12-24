package com.rowan.core.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;

/**
 * @description: 
 * @author: rowan
 * @date: 2020-12-24 22:57:30
 **/
public class AopUtil {
    public static Method getSpecificMethod(ProceedingJoinPoint pjp) {
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        // ultimateTargetClass-终极目标阶层
        // 应该是获取到最终实现的类，而不是代理
        Class<?> aClass = AopProxyUtils.ultimateTargetClass(pjp.getTarget());
        if (aClass == null && pjp.getTarget() != null) {
            aClass = pjp.getTarget().getClass();
        }
        Method specificMethod = ClassUtils.getMostSpecificMethod(method, aClass);
        specificMethod = BridgeMethodResolver.findBridgedMethod(specificMethod);
        return specificMethod;
    }
}
