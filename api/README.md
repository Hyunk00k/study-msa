## api (Discovery Service)

### Startup & Shutdown
#### Startup
startup-eureka.sh
#### Shutdown
shutdown-eureka.sh
```
curl -X POST http://localhost:9001/actuator/shutdown
curl -X POST http://localhost:9002/actuator/shutdown
curl -X POST http://localhost:9003/actuator/shutdown
```
### Refresh
refresh-api.sh
```
curl -X POST http://localhost:9001/actuator/refresh
curl -X POST http://localhost:9002/actuator/refresh
curl -X POST http://localhost:9003/actuator/refresh
```