package com.rowan;

public class Student implements Person {

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void eat() {

    }

    @Override
    public void drink() {

    }

    public Student(int age) throws Throwable {
        this.age = age;
        this.finalize();
    }
}
