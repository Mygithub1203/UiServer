package org.deeplearning4j.ui.servlet.resources;

import org.deeplearning4j.ui.nearestneighbors.word2vec.NearestNeighborsResource;

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
@Path("word2vec")
public class Word2VecProxy extends NearestNeighborsResource {
    @Context private ServletContext context;
    @Context private HttpServletRequest servletRequest;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getView() {
        return AssetsAccessor.getView(context, servletRequest, "word2vec.ftl");
    }

    public Word2VecProxy() {
        ;
    }

    /**
     * The file path for uploads
     * y
     *
     * @param filePath the file path for uploads
     */
    public Word2VecProxy(String filePath) {
        super(filePath);
    }
}
