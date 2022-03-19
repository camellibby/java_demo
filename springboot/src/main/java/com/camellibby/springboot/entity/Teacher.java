package com.camellibby.springboot.entity;


public class Teacher extends Person {

    public void display() {
        System.out.printf("Teacher %s:%s", this.getName(), this.getAge());
    }
}