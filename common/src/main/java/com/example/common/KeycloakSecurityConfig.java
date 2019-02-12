package com.example.common;

import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;



@Configuration
@EnableWebSecurity
@Order
public class KeycloakSecurityConfig extends AbstractKeycloakSecurityConfig {

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        super.configure(http);
    }

}
