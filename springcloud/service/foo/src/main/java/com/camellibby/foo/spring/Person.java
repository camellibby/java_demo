package com.camellibby.foo.spring;

import org.springframework.stereotype.Component;

@Component
public class Person {
    private String name = "Person";

    public String getName() {
        return name;
    }
}
