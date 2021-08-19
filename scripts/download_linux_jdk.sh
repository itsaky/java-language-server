#!/bin/bash
# Download a copy of linux JDK in jdks/linux

set -e

# Download linux jdk
mkdir -p jdks/linux
cd jdks/linux
curl https://download.java.net/java/GA/jdk13/5b8a42f3905b406298b72d750b6919f6/33/GPL/openjdk-13_linux-x64_bin.tar.gz > linux.tar.gz
gunzip -c linux.tar.gz | tar xopf -
rm linux.tar.gz
cd ../..