package springboot3.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringBoot3SecController {

    @GetMapping("/spr3")
    public String spr3(){
        return "Spring Boot 3 demo";
    }
}
