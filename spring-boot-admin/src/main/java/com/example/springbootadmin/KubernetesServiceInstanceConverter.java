package com.example.springbootadmin;

import de.codecentric.boot.admin.server.cloud.discovery.DefaultServiceInstanceConverter;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import org.slf4j.Logger;

import static org.springframework.util.StringUtils.isEmpty;

public class KubernetesServiceInstanceConverter extends DefaultServiceInstanceConverter {
    private static final Logger logger = LoggerFactory.getLogger(DefaultServiceInstanceConverter.class);

    @Autowired
    Environment environment;

    @Override
    protected URI getServiceUrl(ServiceInstance instance) {
        URI uri = UriComponentsBuilder.fromUri(instance.getUri())
                .scheme(environment.getProperty("api.gateway.scheme"))
                .host(environment.getProperty("api.gateway.host"))
                .port(environment.getProperty("api.gateway.port"))
                .path("/evolv/" + instance.getServiceId()).build().toUri();
        return uri;
    }

    @Override
    protected URI getManagementUrl(ServiceInstance instance) {
        URI serviceUrl = super.getManagementUrl(instance);
        return UriComponentsBuilder.fromUri(serviceUrl)
                .host(environment.getProperty("api.gateway.host"))
                .port(environment.getProperty("api.gateway.port"))
                .build()
                .toUri();
    }
}
