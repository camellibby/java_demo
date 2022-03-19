package com.camellibby.cycle.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class B {
    @Autowired
    public A a;

    public A getA() {
        return a;
    }

    @Autowired
    public C c;

    public C getC() {
        return c;
    }

    public String hello(String name) {
        String msg = "B hello " + name;
        System.out.println(msg);
        return msg;
    }
}
