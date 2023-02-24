package resourceserver.resourceServer.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceDemoController {

    @GetMapping("/resource")
    public String resource(Authentication a){
        return "Resource 1";
    }
}
