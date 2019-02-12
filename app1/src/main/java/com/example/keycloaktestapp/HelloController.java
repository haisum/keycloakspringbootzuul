package com.example.keycloaktestapp;

import lombok.extern.java.Log;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RestController
@Log
public class HelloController {

    @Autowired
    KeycloakRestTemplate keycloakRestTemplate;

    @Autowired
    Environment environment;

    @GetMapping("/")
    public String helloWorld(){
        return "hello world app 1";
    }

    @GetMapping("/secure")
    public String secureHelloWorld(Principal principal){
        return "secure hello world app 1 " + principal.getName();
    }

    @GetMapping("/secure/relay")
    public String secureRelayHelloWorld(){
        log.info("Requesting: " + environment.getProperty("api.gateway.url") + "/evolv/app2/secure");
        ResponseEntity<String> response = keycloakRestTemplate.getForEntity(environment.getProperty("api.gateway.url") + "/evolv/app2/secure", String.class);
        return "secure hello world app 2 response: " + response.getBody();
    }

}
