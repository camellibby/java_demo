package com.camellibby.ioc.processor.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Order implements BeanNameAware, ApplicationContextAware, BeanFactoryAware {
    public Order() {
        System.out.println("Order constructor");
    }

    public void display() {
        System.out.println("Order instance display");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Order setBeanName");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Order setBeanFactory");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Order setApplicationContext");
    }
}
