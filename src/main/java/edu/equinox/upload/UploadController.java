package edu.equinox.upload;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.multipart.FileUpload;

@Path("/upload")
@ApplicationScoped
public class UploadController {

    @Inject
    UploadService uploadService;

    @POST
    @Path("/single")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response multipart(@RestForm("doc") FileUpload file) throws Exception {
        var saved = uploadService.save(file);

        return Response.ok(saved).build();
    }
}