package org.deeplearning4j.ui.servlet.resources;

import org.deeplearning4j.ui.tsne.TsneResource;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import javax.ws.rs.Path;

/**
 * @author raver119@gmail.com
 */
@Path("tsne")
public class TsneProxy extends TsneResource {
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
