package com.rowan.load;

public class Cups2 {

    static Cup c1;
    static Cup c2;

    {
        c1 = new Cup(1);
        c2 = new Cup(2);
        System.out.println("c1和c2创建完成");
    }

    public Cups2() {
        System.out.println("Cups2()");
    }
}
