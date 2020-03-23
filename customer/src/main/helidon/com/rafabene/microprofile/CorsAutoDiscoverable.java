package com.rafabene.microprofile;

import javax.ws.rs.core.FeatureContext;

import org.glassfish.jersey.internal.spi.AutoDiscoverable;

public class CorsAutoDiscoverable implements AutoDiscoverable {

    @Override
    public void configure(FeatureContext context) {
        context.register(CorsFilter.class);
    }
}