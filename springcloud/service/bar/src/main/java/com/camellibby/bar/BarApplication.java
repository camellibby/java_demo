package com.camellibby.bar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BarApplication {
    public static void main(String[] args) {
        SpringApplication.run(BarApplication.class);
    }
}
