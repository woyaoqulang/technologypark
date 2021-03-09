package com.rowan.polymorphism;

public class Insect {
    int i = 9;
    int j;

    public Insect() {
        prt("i=" + i + "，j=" + j);
        j = 39;
    }

    static int x1 = prt("this insect.x1");

    static int prt(String s) {
        System.out.println(s);
        return 47;
    }
}
