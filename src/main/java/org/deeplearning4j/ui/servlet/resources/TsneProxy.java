package org.deeplearning4j.ui.servlet.resources;

import org.deeplearning4j.ui.tsne.TsneResource;

/**
 * @author raver119@gmail.com
 */
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
