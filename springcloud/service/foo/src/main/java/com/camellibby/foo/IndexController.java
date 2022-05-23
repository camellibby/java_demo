package com.camellibby.foo;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@AllArgsConstructor
public class IndexController {
    private final FooConfig fooConfig;

    @GetMapping("props")
    public String getProps() {
        return fooConfig.getName();
    }
}
