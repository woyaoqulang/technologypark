package com.rowan.demo;

import com.rowan.core.constant.HttpStatusEnum;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @description: 测试代码
 * @author: rowan
 * @date: 2020-12-24 23:01:54
 **/
public class Demo1 {

    public static void main(String[] args) {
        String s = "00012355666";
        s = s.replaceAll("^(0+)", "");
        System.out.println(s);
        System.out.println(HttpStatusEnum.getMessage(200));
    }


    public static void test(Map<String, Set<String>> queueMap) {
        Set<String> strings = new HashSet<>();
        strings.add("1234");
        queueMap.put(queueMap.size() + "", strings);
        int size = 10;
        if (queueMap.size() < size) {
            test(queueMap);
        }
        int t = 89;
        String exChars = "fff";
        System.out.println(t <= 65);
    }
}
