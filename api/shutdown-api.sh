#!/bin/bash

curl -X POST http://localhost:9001/actuator/shutdown
curl -X POST http://localhost:9002/actuator/shutdown
curl -X POST http://localhost:9003/actuator/shutdown