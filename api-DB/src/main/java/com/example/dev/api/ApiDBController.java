package com.example.dev.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RefreshScope
public class ApiDBController {

    @Value("${server.port}")
    private String serverPort;
    @Value("${service.name}")
    private String serviceName;

    @Autowired
    private EmployeesRepository repository;

    public ResponseEntity<Map<String, Object>> breaker() {
        log.info(" serverPort : {} serviceName : {} ", serverPort, serviceName);
        Map<String, Object> breakerResponseMqp = new HashMap();
        breakerResponseMqp.put("method", "breaker");
        breakerResponseMqp.put("serverPort", serverPort);
        breakerResponseMqp.put("serviceName", serviceName);
        return ResponseEntity
                .status(302)
                .contentType(MediaType.APPLICATION_JSON)
                .body(breakerResponseMqp);
    }

    @GetMapping("/internal/response1")
    @HystrixCommand(groupKey = "study", commandKey = "resposne", fallbackMethod = "breaker")
    public ResponseEntity<Map<String, Object>> response1() {
        log.info(" serverPort : {} serviceName : {} ", serverPort, serviceName);

        Map<String, Object> responseMqp = new HashMap();
        responseMqp.put("method", "response1");
        responseMqp.put("serverPort", serverPort);
        responseMqp.put("serviceName", serviceName);
        responseMqp.put("employee", repository.findById((long) 1));

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseMqp);
    }

    @GetMapping("/internal/response2")
    @HystrixCommand(groupKey = "custom-th", commandKey = "custom-cm", fallbackMethod = "breaker")
    public ResponseEntity<Map<String, Object>> response2() {
        log.info(" serverPort : {} serviceName : {} ", serverPort, serviceName);

        Map<String, Object> responseMqp = new HashMap();
        responseMqp.put("method", "response2");
        responseMqp.put("serverPort", serverPort);
        responseMqp.put("serviceName", serviceName);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseMqp);
    }


    @GetMapping("/error")
    @HystrixCommand(groupKey = "custom-th", commandKey = "custom-cm", fallbackMethod = "breaker")
    public ResponseEntity<Map<String, Object>> error() throws Exception {
        log.info(" serverPort : {} serviceName : {} ", serverPort, serviceName);
        throw new RuntimeException("RuntimeException");
    }
}