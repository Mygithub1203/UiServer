package org.deeplearning4j.ui.servlet.resources;

import org.deeplearning4j.ui.activation.ActivationsResource;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author raver119@gmail.com
 */
@Path("activations")
public class ActivationsProxy extends ActivationsResource {
    @Context
    private ServletContext context;

    @Context private HttpServletRequest servletRequest;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getView() {
        return AssetsAccessor.getView(context, servletRequest, "activations.ftl");
    }
}
