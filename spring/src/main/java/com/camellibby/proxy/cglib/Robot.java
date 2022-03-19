package com.camellibby.proxy.cglib;

import org.springframework.stereotype.Component;

@Component
public class Robot {
    public void test() {
        System.out.println("------test-------");
    }

    public String hello(String name) {
        String formattedName = String.format("'%s'", name);
        System.out.println("------hello " + formattedName + "!------");
        return formattedName;
    }
}