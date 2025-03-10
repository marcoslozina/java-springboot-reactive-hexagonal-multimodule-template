#!/bin/bash
set -e

docker run --rm \
    -v "$(pwd)":/app -w /app \
    eclipse-temurin:23-jdk \
    ./gradlew clean build