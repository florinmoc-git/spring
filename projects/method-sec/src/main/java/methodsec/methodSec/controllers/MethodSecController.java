package methodsec.methodSec.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MethodSecController {

    @GetMapping("/meth1")
    @PreAuthorize("hasAuthority('read')")
    public String methodDemo1(){
        return "Method demo 1";
    }

    @GetMapping("/meth2/{param}")
    @PreAuthorize("#param == authentication.name")
    public String methodDemo2(@PathVariable("param") String param){
        return "Method demo 2";
    }

    // If the condition is long, it can be placed into a class method
    // Useful if you need to debug, as you can put a breakpoint in the said method
    @GetMapping("/meth3/{param}")
    @PreAuthorize("@authorizeConditionEvaluator.checkName(#param)")
    public String methodDemo3(@PathVariable("param") String param){
        return "Method demo 3";
    }
}
