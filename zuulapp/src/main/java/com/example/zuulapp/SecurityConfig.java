package com.example.zuulapp;

import com.example.common.AbstractSecurityConfig;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public class SecurityConfig extends AbstractSecurityConfig {

    @Override
    protected void configure(HttpSecurity http) throws  Exception{
        http.authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll() // for cors
                .antMatchers("/**/actuator/health").permitAll()
                .antMatchers("/**/actuator").permitAll()
                .antMatchers("/**/actuator/**").hasRole("ADMIN")
                .antMatchers("/**").authenticated()
                .and()
                .formLogin();
        super.configure(http);
    }
}
