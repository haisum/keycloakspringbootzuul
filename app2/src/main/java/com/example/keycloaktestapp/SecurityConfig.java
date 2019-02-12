package com.example.keycloaktestapp;

import com.example.common.AbstractSecurityConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public  class SecurityConfig extends AbstractSecurityConfig {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/app2/").permitAll();
        super.configure(http);
    }
}
