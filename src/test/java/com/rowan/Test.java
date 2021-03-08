package com.rowan;

/**
 * @description:
 * @author: zhanghao
 * @date: 2021-2-25 19:31:29
 **/
public class Test {

    public static void main(String[] args) throws Throwable {
       /* String a = new String("1");
        String b = new String("1");

        String c = "123";
        String d = "1" + "2" + "3";*/

        Integer a = 0;
        int b = 0;
        for (int i = 0; i < 99; i++) {
            a = a++;
            b = a++;
        }
        //System.out.println(a);
        //System.out.println(b);
        if (test1(0) && test2(2) && test3(2)) {
            System.out.println("成功");
        } else {
            System.out.println("失败");
        }
        test4();

    }


    public static void test4() {
        int i = 54;
        System.out.println(i >>> 3);
    }


    public static boolean test1(int i) {
        System.out.println("test1-i 的值：" + i);
        return i < 1;
    }

    public static boolean test2(int i) {
        System.out.println("test2-i 的值：" + i);
        return i < 2;
    }

    public static boolean test3(int i) {
        System.out.println("test3-i 的值：" + i);
        return i < 3;
    }


    public static void duSomeThing(int type) {
        type = 134;
        System.out.println(type);
        return;
    }
}
