package org.acme.getting.started;

import java.security.Principal;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/secured")
@ApplicationScoped
public class TokenResource {
    @GET()
    @Path("permit-all")
    @PermitAll 
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(@Context SecurityContext ctx) { 
        Principal caller =  ctx.getUserPrincipal(); 
        String name = caller == null ? "anonymous" : caller.getName();
        String helloReply = String.format("hello + %s, isSecure: %s, authScheme: %s", name, ctx.isSecure(), ctx.getAuthenticationScheme());
        return helloReply; 
    }
}