package com.rowan.polymorphism;

public class FinalValue {
    final int i1 = 9;
    static final int i2 = 99;
    public static final int i3 = 39;
    final int i4 = (int) (Math.random() * 100);
    static final int i5 = (int) (Math.random() * 100);

    Value v1 = new Value();
    final Value v2 = new Value();
    static final Value v3 = new Value();

    public void print(String id) {
        System.out.println(id + "：i4=" + i4 + "，i5=" + i5);
    }

    public static void main(String[] args) {
        FinalValue fd1 = new FinalValue();
        fd1.v1 = new Value();
        //fd1.v2= new Value(); 常量不能修改
        //fd1.v3 = new Value();
        fd1.print("fd1");
        FinalValue fd2 = new FinalValue();
        fd1.print("fd1");
        fd2.print("fd2");

    }
}
