package com.times.lonefitapi.controller;

import com.times.lonefitcore.domain.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String home() {
        return new Test(1L, "test admin").getName();
    }
}
