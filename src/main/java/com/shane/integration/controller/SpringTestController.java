package com.shane.integration.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class SpringTestController {
    @GetMapping("hello")
    public String greetings() {
        return "Hello Spring!";
    }
}
