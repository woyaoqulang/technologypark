package com.rowan.polymorphism;

public class BlankFinal {

    final int i = 0;
    final int j;
    final Poppet p;

    public BlankFinal() {
        this.j = 1;
        this.p = new Poppet();
    }

    public BlankFinal(int j) {
        this.j = j;
        this.p = new Poppet();
    }

    public static void main(String[] args) {
        BlankFinal blankFinal = new BlankFinal();
        System.out.println(blankFinal);
    }
    
}
