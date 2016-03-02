package org.deeplearning4j.ui.servlet.resources;

import org.deeplearning4j.ui.tsne.TsneResource;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

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
@Path("tsne")
public class TsneProxy extends TsneResource {
    @Context private ServletContext context;
    @Context private HttpServletRequest servletRequest;


    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getView() {
        return AssetsAccessor.getView(context, servletRequest, "tsne.ftl");
    }

    public TsneProxy() {
        this(System.getProperty("java.io.tmpdir"));
    }
    /**
     * The file path for uploads
     *
     * @param filePath the file path for uploads
     */
    public TsneProxy(String filePath) {
        super(filePath);
    }
}
