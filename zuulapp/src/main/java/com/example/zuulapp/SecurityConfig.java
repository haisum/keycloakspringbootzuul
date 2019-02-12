package com.example.zuulapp;

import com.example.common.AbstractKeycloakSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends AbstractKeycloakSecurityConfig {

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        super.configure(http);
    }
}
