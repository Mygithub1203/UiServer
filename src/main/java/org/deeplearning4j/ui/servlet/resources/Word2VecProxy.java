package org.deeplearning4j.ui.servlet.resources;

import org.deeplearning4j.ui.nearestneighbors.word2vec.NearestNeighborsResource;

/**
 * @author raver119@gmail.com
 */
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
