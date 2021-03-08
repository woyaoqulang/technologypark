package com.rowan.load;

public class StaticTest {

    static Table t2 = new Table();

    static Cupboard t3 = new Cupboard();

    public static void main(String[] args) {
        new Cupboard();
        t2.f2(1);
        t3.f3(1);
        //输出答案
        /*
         * bowl()1
         * bowl()2
         * table()
         * f()1
         * bowl()4
         * bowl()5
         * bowl()3
         * Cupboard()
         * f()2
         * bowl()3
         * Cupboard()
         * f()2
         * f2()1
         * f3()1
         *
         */
    }
}
