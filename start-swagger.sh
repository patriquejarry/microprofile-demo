#!/bin/bash
open http://localhost/?url=http://localhost:8080/openapi
docker run -p 80:8080 swaggerapi/swagger-ui