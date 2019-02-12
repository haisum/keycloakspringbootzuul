package com.example.zuulapp;

import lombok.extern.java.Log;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.zuul.web.ZuulHandlerMapping;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Log
@Component
public class RouteChangedEventListener implements ApplicationListener<ApplicationEvent> {

    private ZuulHandlerMapping zuulHandlerMapping;

    public RouteChangedEventListener(ZuulHandlerMapping zuulHandlerMapping) {
        this.zuulHandlerMapping = zuulHandlerMapping;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        zuulHandlerMapping.setDirty(true);
    }

}