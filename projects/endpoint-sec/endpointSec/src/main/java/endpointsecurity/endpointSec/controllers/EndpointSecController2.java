package endpointsecurity.endpointSec.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/end2")
public class EndpointSecController2 {

    @GetMapping("/subend1")
    public String end1(){
        return "Endpoint 1";
    }

    @GetMapping("/subend2")
    public String end2(){
        return "Endpoint 2";
    }

    @PostMapping("/subend3")
    public String end2p(){
        return "Endpoint 2 post";
    }
}
