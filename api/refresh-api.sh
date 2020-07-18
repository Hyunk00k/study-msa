#!/bin/bash

curl -X POST http://localhost:9001/actuator/refresh
curl -X POST http://localhost:9002/actuator/refresh
curl -X POST http://localhost:9003/actuator/refresh