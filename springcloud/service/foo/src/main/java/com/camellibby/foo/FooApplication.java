package com.camellibby.foo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class FooApplication {
    public static void main(String[] args) {
        System.getProperties().put("server.port", "8301");
        SpringApplication.run(FooApplication.class, args);
    }
}
