package com.camellibby.aop.aspect;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AspectAopApplication {
    public static void main(String[] args){
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("spring-aop-aspect.xml");
        Robot robot = (Robot) appCtx.getBean("robot");
        System.out.println("---------------------start---------------------------");
        robot.test();
        robot.hello("Jeffrey");
        System.out.println("---------------------end---------------------------");
    }
}
