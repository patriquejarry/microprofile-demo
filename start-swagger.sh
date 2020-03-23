#!/bin/bash
open http://localhost:80
docker run -p 80:8080 swaggerapi/swagger-ui