package com.rowan.polymorphism;

public class Test {

    public static void tune(A a) {
        a.play();
    }

    public static void tune2(InstrumentX i) {
        i.play(NoteX.A);
    }

    public static void main(String[] args) {
        InstrumentX windX = new WindX();
        tune2(windX);
    }
}
