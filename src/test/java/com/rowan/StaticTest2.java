package com.rowan;

public class StaticTest2 {

    static Cups x = new Cups();
    static Cups y = new Cups();

    public static void main(String[] args) {
        Cups.c1.f(99);
        Cups2 cups2 = new Cups2();
        Cups2 cups21 = new Cups2();
        /**
         * Cup()1
         * Cups()
         * Cup()2
         * Cups()
         * Cups()
         * f()99
         */
    }
}
