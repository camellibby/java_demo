package com.camellibby.proxy.cglib;

import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class CglibApplication {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, ".");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(CglibApplication.class);
        Robot robot = ctx.getBean(Robot.class);
        robot.hello("Jeffrey");

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Robot.class);
        enhancer.setCallback(new CustomCglib());
        Robot proxy = (Robot) enhancer.create();
        proxy.hello("Libby");
    }
}
