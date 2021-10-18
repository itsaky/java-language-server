#!/bin/bash
# Download a copy of windows JDK in jdks/windows

set -e

# Download windows jdk
mkdir -p jdks/windows
cd jdks/windows
curl https://download.java.net/java/GA/jdk13/5b8a42f3905b406298b72d750b6919f6/33/GPL/openjdk-13_windows-x64_bin.zip > windows.zip
unzip windows.zip
rm windows.zip
cd ../..