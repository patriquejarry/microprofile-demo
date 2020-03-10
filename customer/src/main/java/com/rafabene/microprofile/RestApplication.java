package com.rafabene.microprofile;


import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
        title = "Customer Service",
        description = "This is the Custmer microservice",
        version = "1.0.0-SNAPSHOT",
        contact = @Contact(
                name = "Rafael Benevides",
                email = "rafabene@gmail.com",
                url = "http://rafabene.com"
        )
))
public class RestApplication extends Application {

}
