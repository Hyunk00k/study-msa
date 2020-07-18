## eureka (Discovery Service)

### Startup & Shutdown
#### Startup
startup-eureka.sh
#### Shutdown
shutdown-eureka.sh
```
curl -X POST http://localhost:8761/actuator/shutdown
curl -X POST http://localhost:8762/actuator/shutdown
```