package com.rowan.demo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Demo1 {

    public static void main(String[] args) {
        String s = "00012355666";
        s = s.replaceAll("^(0+)", "");
        System.out.println(s);
    }


    public static void test(Map<String, Set<String>> queueMap) {
        Set<String> strings = new HashSet<>();
        strings.add("1234");
        queueMap.put(queueMap.size() + "", strings);
        if (queueMap.size() < 10) {
            test(queueMap);
        }


    }
}
