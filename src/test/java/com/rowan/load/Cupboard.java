package com.rowan.load;

public class Cupboard {

    Bowl b3 = new Bowl(3);

    static Bowl b4 = new Bowl(4);

    static void f4() {
        b5.f(6);
        System.out.println("f4()");
    }

    public Cupboard() {
        System.out.println("Cupboard()");
        b4.f(2);
    }

    void f3(int i) {
        System.out.println("f3()" + i);
    }

    static Bowl b5 = new Bowl(5);
}
