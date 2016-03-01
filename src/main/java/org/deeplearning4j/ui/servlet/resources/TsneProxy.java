package org.deeplearning4j.ui.servlet.resources;

import org.deeplearning4j.ui.tsne.TsneResource;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletContext;
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
    @Context
    private ServletContext context;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getView() {
        return AssetsAccessor.getView(context, "tsne.ftl");
    }

    public TsneProxy() {
        ;
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
