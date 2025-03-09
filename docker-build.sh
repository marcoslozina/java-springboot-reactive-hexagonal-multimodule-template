#!/bin/bash
set -e

docker run --rm \
    -v "$(pwd)":/app -w /app \
    eclipse-temurin:21-jdk \
    ./gradlew clean build