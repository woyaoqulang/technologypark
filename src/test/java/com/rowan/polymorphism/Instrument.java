package com.rowan.polymorphism;

public class Instrument {
    public void play(){
        System.out.println("this is play");
    }

    static void tune(Instrument i){
        i.play();
    }
}
