package org.acme.getting.started;


import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.acme.getting.started.service.GreetingService;
import org.jboss.resteasy.annotations.SseElementType;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

@Path("/hello")
public class GreetingResource {
    @Inject
    GreetingService greetingService;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "hello";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/sayhello/{name}")
    public String sayHelloInject(@PathParam String name) {
        return greetingService.sayHello(name);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/sayhello/v2/{name}")
    public Uni<String> sayHelloInjectV2(@PathParam String name) {
        return greetingService.getName(name);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/greeting/{count}/{name}")
    public Multi<String> greetings(@PathParam int count, @PathParam String name) {
        return greetingService.greetings(count, name);
    }

    @GET
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @SseElementType(MediaType.TEXT_PLAIN)
    @Path("/stream/{count}/{name}")
    public Multi<String> greetingsAsStream(@PathParam int count, @PathParam String name) {
        return greetingService.greetings(count, name);
    }

}