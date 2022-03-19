package com.camellibby.aop.proxy;

import com.camellibby.aop.proxy.bean.Robot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProxyAopApplication {
    public static void main(String[] args){
        ApplicationContext appCtx = new ClassPathXmlApplicationContext("spring-aop-proxy.xml");
        Robot robot = (Robot) appCtx.getBean("proxy");
        System.out.println("---------------------start---------------------------");
        robot.test();
        robot.hello("Jeffrey");
        System.out.println("---------------------end---------------------------");
    }
}
