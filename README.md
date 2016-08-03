# DeepLearning4j UiServer

This repository contains the wrapper for both the stand-alone and servlet-compatible DL4j UiServers.

To build it from source, the following commands should be run:
```
git clone https://github.com/deeplearning4j/UiServer.git
cd UiServer
mvn clean install -DskipTests=true
```

As result, you'll see a few files in your target folder, including:
```
deeplearning4j-ui-dropwizard.jar
deeplearning4j-ui-servlet.war
```

The first file is a stand-alone executable jar, with an embedded http server powered by DropWizard.

The other is a standard WAR package, which can be deployed to any servlet server compatible with a Java EE environment.

To use a remote UiServer with your DL4j tasks, here's some example code:
```
...
MultiLayerNetwork model = new MultiLayerNetwork(conf);
model.init();

Note here that the port is 8250 please override this in the dropwizard.yml under src/main/resources
to 0 if you want dynamic binding. We do this so we can make assumptions about how to run ports.

UiConnectionInfo connectionInfo = new UiConnectionInfo.Builder()
        .setAddress("8.8.8.8")
        .setPort(8080)
        .setPath("deeplearning4j-ui-servlet")
        .build();

model.setListeners(Collections.singletonList((IterationListener) new HistogramIterationListener(connectionInfo,listenerFreq)));
```

In this example, we assume that the external web service is used located at 8.8.8.8:8080, and a servlet is deployed at ***deeplearning4j-ui-servlet*** path.

If you're going to use a dropwizard-based executable, then the `setPath()` call should be omitted, since everything will be deployed within the root folder.

Obviously, the network address passed into UiConnectionInfo should be accessible from the host where DL4j is running.
