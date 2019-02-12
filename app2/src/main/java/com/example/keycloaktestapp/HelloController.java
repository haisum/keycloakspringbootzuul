package com.example.keycloaktestapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld(){
        return "hello world app 2";
    }

    @GetMapping("/secure")
    public String secureHelloWorld(Principal principal){
        return "hello secure world app 2, " + principal.getName();
    }

}
