package org.acme.getting.started.controller;

import java.io.File;
import java.util.concurrent.CompletionStage;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import io.quarkus.mailer.reactive.ReactiveMailer;

/**
 * MailController
 */
@Path("/mail")
public class MailController {
    @Inject
    Mailer mailer;
    
    @Inject
    ReactiveMailer reactiveMailer;

    

    @GET
    @Path("/simple")
    public Response sendASimpleEmail() {
        mailer.send(Mail.withText("lukman.olymp46@gmail.com", "A simple email from quarkus", "This is my body"));
        return Response.accepted().build();
    }

    @GET
    @Path("/async")
    public CompletionStage<Response> sendASimpleEmailAsync() {
        return reactiveMailer.send(
                Mail.withText("lukman.olymp46@gmail.com", "A reactive email from quarkus", "This is my body"))
                .subscribeAsCompletionStage()
                .thenApply(x -> Response.accepted().build());
    }

    @GET
    @Path("/html")
    public Response sendingHTML() {
        String body = "<strong>Hello!</strong>" + "\n" +
            "<p>Here is an image for you: <img src=\"cid:my-image@quarkus.io\"/></p>" +
            "<p>Regards</p>";
        mailer.send(Mail.withHtml("lukman.manun46@gmail.com", "An email in HTML", body)
            .addInlineAttachment("quarkus-logo.png",
                new File("/Users/lukmanfyrtio/Documents/file/logo.png"),
                "image/png", "<my-image@quarkus.io>"));
        return Response.accepted().build();
    }
}