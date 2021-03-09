package com.rowan.polymorphism;

public class Wind extends Instrument {

    public static void test(Wind w) {

    }

    public static void main(String[] args) {
        Wind wind = new Wind();
        Instrument.tune(wind);
    }
}
