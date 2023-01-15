package edu.equinox.upload;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.reactive.multipart.FileUpload;
import org.jboss.resteasy.reactive.RestForm;


@Path("/upload")
@ApplicationScoped
public class UploadController {

    @POST
    @Path("/single")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response multipart(@RestForm("doc") FileUpload file) {
        String name = file.name();
        String s = file.contentType();
        var path = file.uploadedFile();
        return Response.accepted().build();
    }
}