package com.rafabene.microprofile.client;

import javax.ws.rs.*;

public class RecommendationException extends WebApplicationException {

    private static final long serialVersionUID = 1L;
    private final int status;

    public RecommendationException(final int status, final String message) {
        super(message);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
