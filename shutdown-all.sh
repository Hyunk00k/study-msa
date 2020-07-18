#!/bin/bash

# api
curl -X POST http://localhost:9001/actuator/shutdown
curl -X POST http://localhost:9002/actuator/shutdown
curl -X POST http://localhost:9003/actuator/shutdown

# eureka
curl -X POST http://localhost:8761/actuator/shutdown
curl -X POST http://localhost:8762/actuator/shutdown

# consumer
curl -X POST http://localhost:10001/actuator/shutdown

# monitor
curl -X POST http://localhost:9010/actuator/shutdown

# force shutdown
ps aux | grep java | grep 0.0.1-SNAPSHOT.jar | awk {'print $2'} | xargs  kill -9