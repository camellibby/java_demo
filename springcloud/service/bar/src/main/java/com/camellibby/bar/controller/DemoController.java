package com.camellibby.bar.controller;

import com.camellibby.bar.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("demo")
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("index")
    public String index() {
        return demoService.index();
    }

    @GetMapping("sentinel")
    public String sentinel() {
        return "sentinel";
    }
}
