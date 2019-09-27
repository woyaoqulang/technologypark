package com.clare;

public class Teacher extends Person {

    public void tell(){
        System.out.println("teacher");
    }

    public Teacher(){
        super.tell();
    }

    public void tell(int aa){
        System.out.println("我是int类型");
    }

    public void tell(Integer aa){
        System.out.println("我是integer");

    }
}
