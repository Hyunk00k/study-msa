#!/bin/bash

gradle clean build
java -jar -Dspring.profiles.active=eureka-1  build/libs/eureka-0.0.1-SNAPSHOT.jar  &
java -jar -Dspring.profiles.active=eureka-2  build/libs/eureka-0.0.1-SNAPSHOT.jar  &