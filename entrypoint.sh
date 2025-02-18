#!/bin/bash

echo "Building the project..."
mvn clean install -f /var/www/app/pom.xml

echo "Running migrations..."

echo "Starting the application..."
java -jar /var/www/app/target/taskify-0.0.1-SNAPSHOT.jar

tail -f /dev/null