package com.rowan;

public class ChairTest {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("java垃圾回收开始");
        }
        while (!Chair.f) {
            new Chair();
            new String("占用空间");
        }
        System.out.println(Chair.created + "---" + Chair.finalized);
        if (args[0].equals("before")) {
            System.out.println("开始");
            System.gc();
            System.out.println("销毁开始");
            System.runFinalization();
        }
        System.out.println("结束");
        if (args[0].equals("after")) {
            System.runFinalizersOnExit(true);
        }
    }
}
