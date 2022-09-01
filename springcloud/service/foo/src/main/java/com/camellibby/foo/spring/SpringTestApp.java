package com.camellibby.foo.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.camellibby.foo.spring")
public class SpringTestApp {
    public static void main(String[] args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringTestApp.class);
        Service service = ctx.getBean(Service.class);
        System.out.println(service.getPerson().getName());
        for (Student student : service.getStudents()) {
            System.out.println(student.getName());
        }
    }
}

