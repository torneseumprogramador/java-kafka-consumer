#!/bin/bash
export CLASSPATH=target/kafka_consomer-1.0-SNAPSHOT.jar
export className=App
echo "## Running $className..."
shift
mvn exec:java -Dexec.mainClass="br.com.kafka_consomer.$className"