package com.rafabene.microprofile;

import static java.lang.String.format;

import java.net.MalformedURLException;
import java.net.URL;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rafabene.microprofile.client.PreferenceException;
import com.rafabene.microprofile.client.PreferenceExceptionWrapper;
import com.rafabene.microprofile.client.PreferenceService;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

@ApplicationScoped
@Path("/api")
@Tag(name = "Customer Service", description = "Get Customer, Preference and Recommendation")
public class CustomerEndpoint {

	@Inject
	@ConfigProperty(name = "preference.api.url",
			defaultValue = "http://localhost:8081/")
	String preferenceURL;

	private static final String RESPONSE_STRING_FORMAT = "customer => %s\n";

	@GET
	@Traced
	@Produces(MediaType.TEXT_PLAIN)
    @Operation(description = "Get Customer, Preference and Recommendation")
	public Response doGet() throws MalformedURLException {
		URL url = new URL(preferenceURL);
		PreferenceService preferenceService = RestClientBuilder
				.newBuilder()
				.baseUrl(url)
				.register(PreferenceExceptionWrapper.class)
				.build(PreferenceService.class);
		try {
			return Response.ok(format(RESPONSE_STRING_FORMAT, preferenceService.getPreference())).build();
		} catch (PreferenceException e) {
			return Response
					.status(Response.Status.SERVICE_UNAVAILABLE)
					.entity(format(RESPONSE_STRING_FORMAT,
							format("Error: %d - %s", e.getStatus(), e.getMessage()))
					)
					.build();
		} catch (Exception e) {
			return Response
					.status(Response.Status.SERVICE_UNAVAILABLE)
					.entity(format(RESPONSE_STRING_FORMAT,
							format("Error: %s", e.getCause()))
					)
					.build();

		}
	}
}
