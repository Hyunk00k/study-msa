#!/bin/bash

gradle clean build
java -jar -Dspring.profiles.active=app-1,local  build/libs/api-0.0.1-SNAPSHOT.jar  &
java -jar -Dspring.profiles.active=app-2,local  build/libs/api-0.0.1-SNAPSHOT.jar  &
java -jar -Dspring.profiles.active=app-3,local  build/libs/api-0.0.1-SNAPSHOT.jar  &