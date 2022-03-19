package com.camellibby.servlet.scan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

@SpringBootApplication
public class ServletScanApplication implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    public static void main(String[] args) {
        SpringApplication.run(ServletScanApplication.class);
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(8876);
    }
}
