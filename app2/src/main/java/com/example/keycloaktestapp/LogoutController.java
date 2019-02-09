package com.example.keycloaktestapp;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.net.URL;

@Controller
@Log
public class LogoutController {

    @Autowired
    Environment env;

    @GetMapping(path = "/logout")
    public String logout(HttpServletRequest request) throws Exception {

        /*
        For rest logout:
        POST http://localhost:8080/auth/realms/<my_realm>/protocol/openid-connect/logout
        Authorization: Bearer <access_token>
        Content-Type: application/x-www-form-urlencoded

        client_id=<my_client_id>&refresh_token=<refresh_token>
        see: https://stackoverflow.com/questions/46689034/logout-user-via-keycloak-rest-api-doesnt-work
         */
        log.info("redirect:"+ env.getProperty("keycloak.auth-server-url") +"/realms/" + env.getProperty("keycloak.realm") + "/protocol/openid-connect/logout?redirect_uri=" + getURLBase(request));
        request.logout();
        return "redirect:"+ env.getProperty("keycloak.auth-server-url") +"/realms/" + env.getProperty("keycloak.realm") + "/protocol/openid-connect/logout?redirect_uri=" + getURLBase(request);
    }

    private String getURLBase(HttpServletRequest request) throws MalformedURLException {

        URL requestURL = new URL(request.getRequestURL().toString());
        String port = requestURL.getPort() == -1 ? "" : ":" + requestURL.getPort();
        return requestURL.getProtocol() + "://" + requestURL.getHost() + port;

    }
}
