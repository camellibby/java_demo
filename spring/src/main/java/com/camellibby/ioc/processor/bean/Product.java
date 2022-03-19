package com.camellibby.ioc.processor.bean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

@Component
public class Product implements BeanNameAware {
    public Product() {
        System.out.println("Product constructor");
    }

    public void display() {
        System.out.println("Product instance display");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Product setBeanName display");
    }
}
