package endpointsecurity.endpointSec.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/end1")
public class EndpointSecController {

    @GetMapping("/subend1")
    public String end1(){
        return "Endpoint 1";
    }

    @GetMapping("/subend2")
    public String end2(){
        return "Endpoint 2";
    }
}
