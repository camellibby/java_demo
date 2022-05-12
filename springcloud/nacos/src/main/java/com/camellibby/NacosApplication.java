package com.camellibby;

import com.alibaba.nacos.sys.env.Constants;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.alibaba.nacos")
@ServletComponentScan
@EnableScheduling
public class NacosApplication {

    public static void main(String[] args) {
        System.setProperty(Constants.STANDALONE_MODE_PROPERTY_NAME, "true");
        SpringApplication.run(com.alibaba.nacos.Nacos.class, args);
    }
}

