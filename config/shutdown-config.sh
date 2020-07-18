#!/bin/bash

curl -X POST http://localhost:9011/actuator/shutdown
curl -X POST http://localhost:9012/actuator/shutdown