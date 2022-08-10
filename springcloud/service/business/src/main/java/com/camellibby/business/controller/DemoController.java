package com.camellibby.business.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo")
@RestController
public class DemoController {
    @GetMapping("/index")
    @SentinelResource("HelloWorld")
    public String index() {
        return "index";
    }
}
