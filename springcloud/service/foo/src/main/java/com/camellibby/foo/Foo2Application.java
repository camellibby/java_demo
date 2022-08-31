package com.camellibby.foo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Foo2Application {
    public static void main(String[] args) {
        System.getProperties().put("server.port", "8311");
        SpringApplication.run(Foo2Application.class, args);
    }
}
