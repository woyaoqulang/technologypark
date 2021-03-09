package com.rowan.polymorphism;

public class Beetle extends Insect {

    int k = prt("this is beetle.k");

    public Beetle() {
        prt("k=" + k);
        prt("j=" + j);
    }

    static int x2 = prt("this is beetle.x2");

    static int prt(String s) {
        System.out.println(s);
        return 63;
    }

    public static void main(String[] args) {
        prt("beetle");
        Beetle b = new Beetle();
        //this insect.x1
        //this is beetle.x2
        //beetle
        //i=9,j=0
        //this is beetle.k
        //k=63
        //j=39

    }
}
