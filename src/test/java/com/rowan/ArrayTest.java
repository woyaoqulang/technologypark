package com.rowan;

public class ArrayTest {

    public static void main(String[] args) {
        int[] a1 = {1, 2, 3, 4, 5, 6};
        int[] a2;
        a2 = a1;
        for (int i = 0; i < a2.length; i++) {
            a2[i]++;
        }
        for (int i = 0; i < a1.length; i++) {
            System.out.println("al[" + i + "] = " + a1[i]);
            System.out.println(a2[i]);
        }

        int a = 10;
        int b = 20;
        int c = 30;
        System.out.println(++a+b+c+b+a++);
    }
}
