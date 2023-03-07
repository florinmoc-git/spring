package com.security.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecDemoController {

    @GetMapping("/demo")
    public String demo(){
        return "Security Demo!";
    }
}
