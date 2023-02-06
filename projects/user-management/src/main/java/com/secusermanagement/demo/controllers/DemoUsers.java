package com.secusermanagement.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoUsers {

    @GetMapping("/users")
    public String usersHello(){
        return "Hello users!";
    }
}
