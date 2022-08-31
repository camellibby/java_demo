package com.camellibby.bar.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@FeignClient(value = "foo", path = "/demo")
public interface DemoService {

    @GetMapping("/index")
    String index();
}
