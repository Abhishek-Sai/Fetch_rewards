package com.fetchproject.fetch_project;

import org.springframework.web.bind.annotation.GetMapping;

public class TestController {
    @GetMapping("/test")
    public String test() {
        return "Testing";
    }
}
