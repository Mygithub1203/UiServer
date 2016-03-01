package org.deeplearning4j.ui.servlet.resources;

import org.deeplearning4j.ui.api.ApiResource;

import javax.servlet.ServletContext;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

/**
 * @author raver119@gmail.com
 */
@Path("api")
public class ApiProxy extends ApiResource {
    @Context
    private ServletContext context;
}
