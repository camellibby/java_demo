package com.camellibby.aop.aspect;

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