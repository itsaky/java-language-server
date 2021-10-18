#!/bin/bash
protoc -I=src/main/protobuf --java_out=src/main/java src/main/protobuf/build.proto
protoc -I=src/main/protobuf --java_out=src/main/java src/main/protobuf/analysis.proto
