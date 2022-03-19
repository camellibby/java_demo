package com.camellibby.ioc.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
public class AnnotationIocApplication {
    public static void main(String[] args) {
        ApplicationContext ctx  = new AnnotationConfigApplicationContext(AnnotationIocApplication.class);
        Person person = ctx.getBean(Person.class);
        person.display();
    }
}
