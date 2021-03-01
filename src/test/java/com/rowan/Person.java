package com.rowan;

public interface Person {

    default void job(String name) {
        System.out.println("jf");
    }

    void eat();

    void drink();


}
