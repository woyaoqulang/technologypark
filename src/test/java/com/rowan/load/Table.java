package com.rowan.load;

public class Table {

    static Bowl b1 = new Bowl(1);

    static void gg (){
        System.out.println("----");
    }

    Table() {
        System.out.println("Table()");
        b2.f(1);
    }

    void f2(int i) {
        System.out.println("f2()" + i);
    }

    static Bowl b2 = new Bowl(2);
}
