package org.deeplearning4j.ui.standalone;

import org.deeplearning4j.ui.UiServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author raver119@gmail.com
 */
public class UiServerExecutable {

    protected static final Logger logger = LoggerFactory.getLogger(UiServerExecutable.class);

    public static void main(String[] args) throws Exception {
        UiServer server = UiServer.getInstance();
        System.out.println("");
        System.out.println("");
        System.out.println("");
        logger.info("Server is available on http://localhost:"+ server.getPort()+"/ or via your external IP address with port " + server.getPort());
    }
}
