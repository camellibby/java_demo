package com.camellibby.springboot.controller;

import com.camellibby.springboot.entity.Config;
import com.camellibby.springboot.entity.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @GetMapping
    public String index() {
        return "demo.html";
    }

    @PostMapping
    @ResponseBody
    public Person index(@RequestBody Config config) {
        config.getPerson().display();
        return config.getPerson();
    }
}
