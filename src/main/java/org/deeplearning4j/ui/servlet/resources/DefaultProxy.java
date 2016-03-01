package org.deeplearning4j.ui.servlet.resources;

import org.deeplearning4j.ui.activation.RenderView;
import org.deeplearning4j.ui.defaults.DefaultResource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.deeplearning4j.ui.defaults.DefaultView;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 * @author raver119@gmail.com
 */
@Path("/")
public class DefaultProxy extends DefaultResource {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getView() {
        return Response.ok("Wow!").build();
    }
}
