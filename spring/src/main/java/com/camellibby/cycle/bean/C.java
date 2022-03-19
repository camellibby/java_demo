package com.camellibby.cycle.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class C {
    @Autowired
    public A a;

    public A getA() {
        return a;
    }

    @Autowired
    public B b;

    public B getB() {
        return b;
    }

    public String hello(String name) {
        String msg = "C hello " + name;
        System.out.println(msg);
        return msg;
    }
}
