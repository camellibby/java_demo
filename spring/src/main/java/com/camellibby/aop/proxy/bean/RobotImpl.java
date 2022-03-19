package com.camellibby.aop.proxy.bean;

public class RobotImpl implements Robot {
    public void test() {
        System.out.println("------test-------");
    }

    public String hello(String name) {
        String formattedName = String.format("'%s'", name);
        System.out.println("------hello " + formattedName + "!------");
        return formattedName;
    }
}