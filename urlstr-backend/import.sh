#!/usr/bin/bash

curl -X POST -H "Content-Type: application/json" -v -d @import.json localhost:8080/import
