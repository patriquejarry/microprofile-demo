package com.rafabene.microprofile.health;

import com.rafabene.microprofile.client.*;
import org.eclipse.microprofile.config.inject.*;
import org.eclipse.microprofile.health.*;
import org.eclipse.microprofile.rest.client.*;

import javax.enterprise.context.*;
import javax.inject.*;
import java.net.*;

@Readiness
@ApplicationScoped
public class PreferenceHealthCheck implements HealthCheck {

    @Inject
    @ConfigProperty(name = "preference.api.url",
            defaultValue = "http://localhost:8081/")
    String preferenceURL;


    @Override
    public HealthCheckResponse call() {
        final HealthCheckResponseBuilder response = HealthCheckResponse.named("preferencesAvailability");
        try {
            final URL url = new URL(preferenceURL);
            final PreferenceService preferenceService = RestClientBuilder.newBuilder().baseUrl(url)
                    .register(PreferenceExceptionWrapper.class).build(PreferenceService.class);
            preferenceService.getPreference();
            return response.up().build();
        } catch (final Exception ex) {
            return response.down().withData("cause", ex.getCause().getMessage()).build();
        }

    }
}
