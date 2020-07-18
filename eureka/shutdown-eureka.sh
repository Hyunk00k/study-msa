#!/bin/bash

curl -X POST http://localhost:8761/actuator/shutdown
curl -X POST http://localhost:8762/actuator/shutdown