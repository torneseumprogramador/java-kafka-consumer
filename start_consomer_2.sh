#!/bin/bash
export KAFKA_HOST="localhost:9092"
export KAFKA_TOPIC="TOPICO_1"
export KAFKA_GROUP_ID_READER="consomer_2"

export className=App
echo "## Running $className..."
mvn exec:java -Dexec.mainClass="br.com.kafka_consumer.$className"