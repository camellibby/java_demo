package com.camellibby.aop.proxy;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class ProxyHandler implements MethodBeforeAdvice, AfterReturningAdvice {


    public void before(Method method, Object[] args, Object target) {
        System.out.println("~~~aop---before---");

    }

    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) {
        System.out.println("~~~aop---afterReturning---");
    }
}