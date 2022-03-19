package com.camellibby.proxy.jdk;

import com.camellibby.proxy.jdk.bean.Robot;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class JdkApplication {
    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JdkApplication.class);
        Robot robot = ctx.getBean(Robot.class);
        robot.hello("Jeffrey");

        Robot proxy = CustomProxy.getProxy(robot);
        proxy.hello("Libby");
    }
}
