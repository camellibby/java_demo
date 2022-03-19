package com.camellibby.servlet.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

@SpringBootApplication
public class ServletRegisterApplication implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {
    public static void main(String[] args) {
        SpringApplication.run(ServletRegisterApplication.class);
    }

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(8875);
    }
}
