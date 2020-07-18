# study-msa
#### cloud example for study


### Develop Environment

/etc/hosts
```
127.0.0.1 eureka-1
127.0.0.1 eureka-2
```


Root 
```
implementation 'org.springframework.boot:spring-boot-starter-actuator'
implementation 'org.springframework.boot:spring-boot-starter-webflux'
implementation 'org.springframework.cloud:spring-cloud-starter-config’
```

|Application|service|ip|port|spring-clodu |
------------ | ------------- | ------------- | ------------- | ------------- 
|API|3|127.0.0.1|9001,9002,9003|eureka-client, hystrix|
|Consumer|1|127.0.0.1|10001|eureka-client, hystrix, ribbon, spring-retry|
|Eureka|2|127.0.0.1|8761,8762|eureka-server|
|Monitor|1|127.0.0.1|9010|eureka-client, turbin, hystrix-dashboard|


