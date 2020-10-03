# microprofile-demo
Eclipse Microprofile APIs Demo

Slides to present this codebase are available at <http://bit.ly/slides-microprofile>

---

# Install
(on each project)
* mvn clean install
* mvn clean package

# Run
* java -jar target/customer.jar
* java -jar target/recommendation.jar
* java -jar target/preference.jar

# API
(on each project : 8080/8081/8082)
* http://localhost:8080/api

# Health
(on each project : 8080/8081/8082)
* http://localhost:8080/health
* http://localhost:8080/health/live
* http://localhost:8080/health/ready

# Metrics
(on each project : 8080/8081/8082)
* http://localhost:8080/metrics
* http://localhost:8080/metrics/base
* http://localhost:8080/metrics/vendor
* http://localhost:8080/metrics/application

# SwaggerAPI
* docker run -d -p 80:8080 swaggerapi/swagger-ui
* http://localhost/?url=http://localhost:8080/openapi

# Jaeger
* http://localhost:16686
* docker run -d --rm --name=jaeger -p5775:5775/udp -p6831:6831/udp -p6832:6832/udp -p5778:5778 -p16686:16686 -p14268:14268 jaegertracing/all-in-one:latest

# Prometheus
* http://localhost:9090
* docker run -d -v \`pwd`/prometheus.yml:/etc/prometheus/prometheus.yml -p 9090:9090 prom/prometheus

# Utils
* https://start.microprofile.io/
* https://bit.ly/slides-microprofile
* https://github.com/rafabene/microprofile-demo (this)
* https://github.com/eclipse/microprofile (Documentation)
* https://github.com/eclipse/microprofile-samples
* https://github.com/eclipse/microprofile-starter

---

# GitHub Eclipse Foundation
* https://github.com/eclipse/microprofile-config
* https://github.com/eclipse/microprofile-health
* https://github.com/eclipse/microprofile-metrics
* https://github.com/eclipse/microprofile-fault-tolerance
* https://github.com/eclipse/microprofile-jwt-auth
* https://github.com/eclipse/microprofile-open-api
* https://github.com/eclipse/microprofile-rest-client
* https://github.com/eclipse/microprofile-opentracing

* https://github.com/eclipse/microprofile-reactive-messaging
* https://github.com/eclipse/microprofile-reactive-streams-operators
* https://github.com/eclipse/microprofile-context-propagation
* https://github.com/eclipse/microprofile-graphql
* https://github.com/eclipse/microprofile-conference
* https://github.com/eclipse/microprofile-marketing
* https://github.com/eclipse/microprofile-lra
* https://github.com/eclipse/microprofile-sandbox
* https://github.com/eclipse/microprofile-service-mesh
* https://github.com/eclipse/microprofile-evolution-process
* ...
