#!/usr/bin/env bash
# Database Setup
set -e
echo "Dropping existing databases.."
dropdb -U postgres -h localhost --if-exists pickyourtrial
echo "Creating base databases"
createdb -U postgres -h localhost  pickyourtrial

#Build Project
./gradlew clean build

#Start the service
java -Dspring.config.location=src/main/resources/application.yml  -jar build/libs/referee.jar