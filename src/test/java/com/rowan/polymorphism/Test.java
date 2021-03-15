package com.rowan.polymorphism;

import java.net.SocketTimeoutException;

public class Test {

    public static void tune(A a) {
        a.play();
    }

    public static void tune2(InstrumentX i) {
        System.out.println(i);
        i.play(NoteX.A);
    }

    public static void main(String[] args) {
        WindX windX = new WindX();
        tune2(windX);
        System.out.println(windX);

    }
}
