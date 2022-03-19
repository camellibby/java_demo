package com.camellibby.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class CustomProxy {
    public static <T> T getProxy(T robot) {
        ClassLoader classLoader = robot.getClass().getClassLoader();
        Class<?>[] interfaces = robot.getClass().getInterfaces();
        InvocationHandler invocationHandler = (proxy, method, args) -> {
            System.out.println("invoke by proxy instance");
            return method.invoke(robot, args);
        };
        Object proxy = Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);
        return (T) proxy;
    }
}