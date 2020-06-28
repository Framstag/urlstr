#!/usr/bin/bash

curl -X POST -H "Content-Type: application/json" -v -d '{"url":"https://quarkus.io/"}' localhost:8080/article
