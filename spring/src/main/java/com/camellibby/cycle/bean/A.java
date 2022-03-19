package com.camellibby.cycle.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class A {
    @Autowired
    public B b;

    public B getB() {
        return b;
    }

    @Autowired
    public C c;

    public C getC() {
        return c;
    }

    public String hello(String name) {
        String msg = "A hello " + name;
        System.out.println(msg);
        return msg;
    }
}
