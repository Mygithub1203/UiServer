package org.deeplearning4j.ui.servlet.resources;

import org.deeplearning4j.ui.nearestneighbors.word2vec.NearestNeighborsResource;

import javax.ws.rs.Path;

/**
 * @author raver119@gmail.com
 */
@Path("word2vec")
public class Word2VecProxy extends NearestNeighborsResource {
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
