# DeepLearning4j UiServer

This repository contains wrapper for both standalone and servlet-compatible DL4j UiServer.

To build it from sources following commands should be considered:
```
git clone https://github.com/deeplearning4j/UiServer.git
cd UiServer
mvn clean install -DskipTests=true
```

As result you'll get few files in target/ folder, including:
```
deeplearning4j-ui-dropwizard.jar
deeplearning4j-ui-servlet.war
```

The first one is standalone executable jar, with embedded http server powered by DropWizard.
The next one, is standart WAR package, that can be deployed to any servlet server compatible with Java EE environment.


To use remote UiServer with your DL4j tasks, there's example code available:
```
...
MultiLayerNetwork model = new MultiLayerNetwork(conf);
model.init();

UiConnectionInfo connectionInfo = new UiConnectionInfo.Builder()
        .setAddress("8.8.8.8")
        .setPort(8080)
        .setPath("deeplearning4j-ui-servlet")
        .build();

model.setListeners(Collections.singletonList((IterationListener) new HistogramIterationListener(connectionInfo,listenerFreq)));
```

In this example we assume that external web service is used located at 8.8.8.8:8080, and servlet is deployed at ***deeplearning4j-ui-servlet*** path.
If you're going to use dropwizard-based executable, then setPath() call should be omitted, since everything will be deployed within root folder.
