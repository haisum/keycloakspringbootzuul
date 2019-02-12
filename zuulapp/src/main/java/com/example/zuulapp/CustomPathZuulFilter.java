package com.example.zuulapp;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.REQUEST_URI_KEY;

@Component
@Log
public class CustomPathZuulFilter extends ZuulFilter
{

    @Autowired
    Environment environment;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER + 1;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        return context.get("proxy") != null;
    }

    @Override
    public Object run() {
        RequestContext context = RequestContext.getCurrentContext();
        Object originalRequestPath = context.get(REQUEST_URI_KEY);
        log.info("original request path: " + originalRequestPath);
        String modifiedRequestPath = environment.getProperty("zuul.prefix") + "/" + context.get("proxy") + originalRequestPath;
        log.info("modified request path: " + modifiedRequestPath);
        context.put(REQUEST_URI_KEY, modifiedRequestPath);
        return null;
    }
}