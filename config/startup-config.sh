#!/bin/bash

gradle clean build
java -jar -Dspring.profiles.active=config-1,native  build/libs/config-0.0.1-SNAPSHOT.jar  &
java -jar -Dspring.profiles.active=config-2,native  build/libs/config-0.0.1-SNAPSHOT.jar  &