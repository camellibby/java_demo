package com.camellibby.foo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FooApplication {
    public static void main(String[] args) {
        System.getProperties().put("csp.sentinel.dashboard.server", "localhost:8862");
        System.getProperties().put("project.name", "foo");
        SpringApplication.run(FooApplication.class, args);
    }
}
