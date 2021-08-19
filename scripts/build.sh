#!/bin/bash

set -e

# Needed once
if [ ! -e node_modules ]; then
    npm install
fi

# Build standalone java
if [ ! -e dist/linux/bin/java ]; then
    ./scripts/link_linux.sh
fi

# Compile sources
if [ ! -e src/main/java/com/google/devtools/build/lib/analysis/AnalysisProtos.java ]; then
    ./scripts/gen_proto.sh
fi
./scripts/format.sh
mvn package -DskipTests

# Build vsix
npm run-script vscode:build

code --install-extension build.vsix --force

echo 'Reload VSCode to update extension'
