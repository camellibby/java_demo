package com.camellibby.ioc.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XmlIocApplication {
    public static void main(String[] args) {
        ApplicationContext ctx  = new ClassPathXmlApplicationContext("spring-ioc.xml");
        Person person = ctx.getBean("person", Person.class);
        person.display();
    }
}
