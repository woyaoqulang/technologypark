package com.rowan.jvm;

public class Chair {

    static boolean gcrun = false;
    static boolean f = false;
    static int created = 0;
    static int finalized = 0;
    int i;

    public Chair() {
        i = ++created;
        if (created == 47) {
            System.out.println("Created : 47");
        }
    }

    protected void finalize() {
        if (!gcrun) {
            gcrun = true;
            System.out.println("对象在创建之后，开始销毁" + created);
        }
        if (i == 47) {
            System.out.println("停止销毁");
            f = true;
        }
        finalized++;
        if (finalized >= created) {
            System.out.println("全部销毁了" + finalized);
        }

    }

}
