# HTTP request tracing experiment

## Steps to reproduce

* Start Zipkin Server by executing zipkin-server-2.24.3-exec.jar
* Run service-registry
* Run traceable-service-b
* Run traceable-service-a

Navigate browser to: http://localhost:9411/zipkin/ and hit `Run Query` to see trace result.