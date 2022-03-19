package com.camellibby.aop.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan
@EnableAspectJAutoProxy
public class AnnotationAopApplication {
    public static void main(String[] args){
        ApplicationContext appCtx = new AnnotationConfigApplicationContext(AnnotationAopApplication.class);
        Robot robot = appCtx.getBean(Robot.class);
        System.out.println("---------------------start---------------------------");
        robot.test();
        robot.hello("Jeffrey");
        System.out.println("---------------------end---------------------------");
    }
}
