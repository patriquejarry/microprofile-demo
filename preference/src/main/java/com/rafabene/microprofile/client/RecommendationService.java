package com.rafabene.microprofile.client;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api")
@RegisterRestClient
public interface RecommendationService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRecommendation() throws RecommendationException;

}
