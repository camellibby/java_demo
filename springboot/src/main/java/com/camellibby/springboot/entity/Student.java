package com.camellibby.springboot.entity;


public class Student extends Person {

    public void display() {
        System.out.printf("Student %s:%s", this.getName(), this.getAge());
    }
}