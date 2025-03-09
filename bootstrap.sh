#!/bin/bash
set -e

echo "Setting up environment using SDKMAN..."
sdk env || { echo "Error loading SDKMAN environment"; exit 1; }
echo "Environment set: Java=$(java -version 2>&1 | head -n 1), Gradle=$(gradle -v | head -n 1)"