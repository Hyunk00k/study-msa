package com.example.dev.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
@RestController
public class ApiController {

    @Value("${server.port}")
    private String serverPort;
    private ExecutorService executorService;

    public ResponseEntity<Map<String, Object>> breaker() {
        log.info(serverPort);
        Map<String, Object> breakerResponseMqp = new HashMap();
        breakerResponseMqp.put("method", "breaker");
        breakerResponseMqp.put("serverPort", serverPort);
        return ResponseEntity
                .status(302)
                .contentType(MediaType.APPLICATION_JSON)
                .body(breakerResponseMqp);
    }

    @GetMapping("/response1")
    @HystrixCommand(groupKey = "study", commandKey = "resposne", fallbackMethod = "breaker")
    public ResponseEntity<Map<String, Object>> response1() {
        log.info(serverPort);
        Map<String, Object> responseMqp = new HashMap();
        responseMqp.put("method", "response1");
        responseMqp.put("serverPort", serverPort);
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseMqp);
    }

    @GetMapping("/response2")
    @HystrixCommand(groupKey = "custom-th", commandKey = "custom-cm", fallbackMethod = "breaker")
    public ResponseEntity<Map<String, Object>> response2() {
        log.info(serverPort);
        Map<String, Object> responseMqp = new HashMap();
        responseMqp.put("method", "response2");
        responseMqp.put("serverPort", serverPort);
        executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new Worker());
        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(responseMqp);
    }

    @GetMapping("/error")
    @HystrixCommand(groupKey = "custom-th", commandKey = "custom-cm", fallbackMethod = "breaker")
    public ResponseEntity<Map<String, Object>> error() throws Exception {
        log.info(serverPort);
        throw new RuntimeException("RuntimeException");
    }
}