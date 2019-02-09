package com.example.keycloaktestapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
public class HelloController {

    @GetMapping("/")
    public String helloWorld(){
        return "hello world app 1";
    }

    @GetMapping("/secure")
    public String secureHelloWorld(Principal principal){
        return "secure hello world app 1 " + principal.getName();
    }

}