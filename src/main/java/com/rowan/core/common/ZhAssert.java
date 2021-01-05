package com.rowan.core.common;

import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Map;

/**
 * @description:
 * @author: rowan
 * @date: 2020-12-29 18:10:42
 **/
public class ZhAssert extends Assert {

    public static void state(boolean expression, String message) {
        Assert.state(expression, message);
    }

    public static void isTrue(boolean expression, String message) {
        Assert.isTrue(expression, message);
    }

    public static void isNull(Object object, String message) {
        Assert.isNull(object, message);
    }

    public static void notNull(Object object, String message) {
        Assert.notNull(object, message);
    }

    public static void hasLength(String text, String message) {
        Assert.hasLength(text, message);
    }

    public static void hasText(String text, String message) {
        Assert.hasText(text, message);
    }

    public static void doesNotContain(String textToSearch, String substring, String message) {
        Assert.doesNotContain(textToSearch, substring, message);
    }

    public static void notEmpty(Object[] array, String message) {
        Assert.notEmpty(array, message);
    }

    public static void noNullElements(Object[] array, String message) {
        Assert.noNullElements(array, message);
    }

    public static void notEmpty(Collection<?> collection, String message) {
        Assert.notEmpty(collection, message);
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        Assert.notEmpty(map, message);
    }

    public static void isInstanceOf(Class<?> type, Object obj, String message) {
        Assert.isInstanceOf(type, obj, message);
    }

    public static void isInstanceOf(Class<?> type, Object obj) {
        Assert.isInstanceOf(type, obj);
    }

    public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
        Assert.isAssignable(superType, subType, message);
    }

    public static void isAssignable(Class<?> superType, Class<?> subType) {
        Assert.isAssignable(superType, subType);
    }

}
