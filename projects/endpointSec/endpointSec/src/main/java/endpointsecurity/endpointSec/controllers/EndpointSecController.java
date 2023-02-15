package endpointsecurity.endpointSec.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EndpointSecController {

    @GetMapping("/end1")
    public String end1(){
        return "Endpoint 1";
    }
}
