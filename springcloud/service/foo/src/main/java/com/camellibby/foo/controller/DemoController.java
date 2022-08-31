package com.camellibby.foo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/demo")
@RestController
public class DemoController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/index")
    public String index(HttpServletRequest request) {
        return "remoteAddr: " + request.getRemoteAddr() + ":" + request.getRemotePort() + ";localAddr:" + request.getLocalAddr() + ":" + request.getLocalPort();
    }

    @GetMapping("/lb")
    public ResponseEntity<String> loadBalance() {
        return restTemplate.exchange("http://foo/demo/index", HttpMethod.GET, null, String.class, new Object());
    }
}

