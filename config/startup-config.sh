#!/bin/bash

APP_NAME="config";

echo ${APP_NAME}

gradle clean build
java -jar -Dspring.profiles.active=${APP_NAME}-1  build/libs/${APP_NAME}-0.0.1-SNAPSHOT.jar  &
java -jar -Dspring.profiles.active=${APP_NAME}-2  build/libs/${APP_NAME}-0.0.1-SNAPSHOT.jar  &