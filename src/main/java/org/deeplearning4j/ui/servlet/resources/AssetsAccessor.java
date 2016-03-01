package org.deeplearning4j.ui.servlet.resources;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * This is wrapper for /assets stuff, required to pass everything through jersey
 *
 * @author raver119@gmail.com
 */
@Path("/assets")
public class AssetsAccessor {

    @Context protected ServletContext ctx;

    @GET
    @Path("{subResources: [a-zA-Z][a-zA-Z0-9_/\\-.]+}")
    public Response getResource(@PathParam("subResources") String subResources) {
        subResources = subResources.replaceAll("\\.{2,}","");
        String outputType = MediaType.TEXT_PLAIN;
        if (subResources.endsWith(".img")) {
            try {
                InputStream contentStream = ctx.getResourceAsStream("/assets/" + subResources);
                if (contentStream == null) return Response.status(Response.Status.NOT_FOUND).build();

                outputType = "image/png";
                return Response.ok(contentStream).type(outputType).build();
            } catch (Exception e) {
                Response.status(Response.Status.NOT_FOUND).build();
            }
        } else {
            try {
                InputStream contentStream = ctx.getResourceAsStream("/assets/" + subResources);
                if (contentStream == null) return Response.status(Response.Status.NOT_FOUND).build();

                return Response.ok(contentStream).type(outputType).build();
            } catch (Exception e) {
                throw new RuntimeException(e);
                //Response.status(Response.Status.NOT_FOUND).build();
            }
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    /**
     * PLEASE NOTE: This method is not mapped into Jersey space, and should NOT be mapped
     *
     * @param file
     * @return
     */
    public static Response getView(ServletContext ctx, HttpServletRequest servletRequest, String file) {

        try {
            InputStream contentStream = ctx.getResourceAsStream("/views/" + file);
            if (contentStream == null) return Response.status(Response.Status.NOT_FOUND).build();

            /*
                Load everything, and fix paths
             */
            BufferedReader reader = new BufferedReader(new InputStreamReader(contentStream));
            StringBuilder builder = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            String content = builder.toString();
            String path = servletRequest.getPathInfo();

            content = content.replaceAll("(\\.|\\..|)\\/assets\\/","ROOKIE9821nZas");
            if (path.endsWith("/") && !file.equals("default.ftl")) {
                content = content.replaceAll("ROOKIE9821nZas","../assets/");
            } else {
                content = content.replaceAll("ROOKIE9821nZas","./assets/");
            }

            return Response.ok(content).type(MediaType.TEXT_HTML_TYPE).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //Response.status(Response.Status.BAD_REQUEST).build();
        }

      //  return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_HTML_TYPE).build();
    }
}
