package com.rafabene.microprofile;


import static java.lang.String.format;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rafabene.microprofile.client.RecommendationExceptionWrapper;
import com.rafabene.microprofile.client.RecommendationService;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Bulkhead;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.RestClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
@Path("/api")
@Tag(name = "Preference Service", description = "Get Preference and Recommendation")
public class PreferenceEndpoint {


    @Inject
    @ConfigProperty(name = "recommendation.api.url",
            defaultValue = "http://localhost:8082/")
    String recommendationURL;

    private static final String RESPONSE_STRING_FORMAT = "preference => %s\n";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Traced
    @Retry(maxRetries = 5)
   // @Timeout(500)
   // @Fallback(fallbackMethod = "fallback")
   // @CircuitBreaker(requestVolumeThreshold = 4, failureRatio = 0.5 )
    @Operation(description = "Get Preference and Recommendation")
    public Response doGet() throws MalformedURLException, InterruptedException {
        // Thread.sleep(10 * 1000);
        URL url = new URL(recommendationURL);
        RecommendationService recommendationService = RestClientBuilder
                .newBuilder()
                .baseUrl(url)
                .register(RecommendationExceptionWrapper.class)
                .build(RecommendationService.class);
            return Response.ok(format(RESPONSE_STRING_FORMAT, recommendationService.getRecommendation())).build();
    }

    public Response fallback(){
        logger.info("Fallback method invoked");
        return Response.ok("FallBack method").build();
    }

}
