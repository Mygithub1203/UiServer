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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * This is wrapper for /assets stuff, required to pass everything through jersey
 *
 * @author raver119@gmail.com
 */
@Path("/assets")
public class AssetsAccessor {

    @Context protected ServletContext ctx;
    @Context private HttpServletRequest servletRequest;

    private static Pattern p = Pattern.compile("(\\/)(?:weights|api|word2vec|flow|arbiter|activations|tsne|sessions|whatsup|events)");

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
        } else if (subResources.endsWith(".js")) {
            try {
                InputStream contentStream = ctx.getResourceAsStream("/assets/" + subResources);
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
                //String content = builder.toString();
                String path = servletRequest.getRequestURI();

                String nPath = getPrefix(path, "assets");
                Matcher m = p.matcher(builder);
                while (m.find()) {
                    int position = m.start();
                    builder.replace(position, position+1, "ALPHA8812mZ");
                }
                String content = builder.toString().replaceAll("ALPHA8812mZ",nPath);

                return Response.ok(content).type(outputType).build();
            } catch (Exception e) {
                //Response.status(Response.Status.NOT_FOUND).build();
                throw new RuntimeException(e);
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
            String nPath = getPrefix(servletRequest.getRequestURI(),"sdsds");
            content = content.replaceAll("href=\"/\"","href=\"" + nPath + "\"");

            builder = new StringBuilder(content);
            Matcher m = p.matcher(builder);
            while (m.find()) {
                int position = m.start();
                builder.replace(position, position+1, "ALPHA8812mZ");
            }
            content = builder.toString().replaceAll("ALPHA8812mZ",nPath);

            return Response.ok(content).type(MediaType.TEXT_HTML_TYPE).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
            //Response.status(Response.Status.BAD_REQUEST).build();
        }

      //  return Response.status(Response.Status.NOT_FOUND).type(MediaType.TEXT_HTML_TYPE).build();
    }

    private static String getPrefix(String path, String stop) {
        StringBuilder prefix = new StringBuilder();
        String[] split = path.split("\\/");
        int cnt = 0;
        for (String chunk: split) {
            if (chunk.equals(stop) || cnt == split.length - 1)
                break;
            prefix.append("/").append(chunk);
            cnt++;
        }

        prefix.append("/");
        return  prefix.toString().replaceAll("\\/{2,}","/");
    }
}
