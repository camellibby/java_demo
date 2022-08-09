package com.camellibby.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class GatewayApplication {
    public static void main(String[] args) {
        // System.getProperties().put("csp.sentinel.dashboard.server", "localhost:8862");
        // System.getProperties().put("project.name", "gateway");
        SpringApplication.run(GatewayApplication.class, args);
    }
}
