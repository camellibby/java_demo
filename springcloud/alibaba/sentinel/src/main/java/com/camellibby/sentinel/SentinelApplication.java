package com.camellibby.sentinel;

import com.alibaba.csp.sentinel.dashboard.DashboardApplication;
import com.alibaba.csp.sentinel.init.InitExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SentinelApplication {
    public static void main(String[] args) {
        triggerSentinelInit();
        SpringApplication.run(DashboardApplication.class, args);
    }

    private static void triggerSentinelInit() {
        (new Thread(() -> {
            InitExecutor.doInit();
        })).start();
    }
}
