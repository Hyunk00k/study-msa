#!/bin/bash

gradle clean build
java -jar -Dspring.profiles.active=app-1  build/libs/api-0.0.1-SNAPSHOT.jar  &
java -jar -Dspring.profiles.active=app-2  build/libs/api-0.0.1-SNAPSHOT.jar  &
java -jar -Dspring.profiles.active=app-3  build/libs/api-0.0.1-SNAPSHOT.jar  &