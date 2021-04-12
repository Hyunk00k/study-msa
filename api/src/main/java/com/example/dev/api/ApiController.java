package com.example.dev.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@RefreshScope
public class ApiController {

  @Autowired
  InternalClient internalClient;

  @Value("${server.port}")
  private String serverPort;

  @Value("${service.name}")
  private String serviceName;

  private ExecutorService executorService;

  public Mono<ResponseEntity<Map<String, Object>>> breaker() {
    log.info(" serverPort : {} serviceName : {} ", serverPort, serviceName);

    Map<String, Object> breakerResponseMqp = new HashMap();
    breakerResponseMqp.put("method", "breaker");
    breakerResponseMqp.put("serverPort", serverPort);
    breakerResponseMqp.put("serviceName", serviceName);
    return Mono.just(
      ResponseEntity.status(302)
        .contentType(MediaType.APPLICATION_JSON)
        .body(breakerResponseMqp));
  }

  @GetMapping("/response1")
  @HystrixCommand(groupKey = "study", commandKey = "response", fallbackMethod = "breaker")
  public Mono<ResponseEntity<Map<String, Object>>> response1() {
    log.info(" serverPort : {} serviceName : {} ", serverPort, serviceName);

    Map<String, Object> responseMqp = new HashMap();
    responseMqp.put("method", "response1");
    responseMqp.put("serverPort", serverPort);
    responseMqp.put("serviceName", serviceName);
    responseMqp.put("internalClient", internalClient.response1().toString());
    return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseMqp));
  }

  @GetMapping("/response2")
  @HystrixCommand(groupKey = "custom-th", commandKey = "custom-cm", fallbackMethod = "breaker")
  public Mono<ResponseEntity<Map<String, Object>>> response2() {
    log.info(" serverPort : {} serviceName : {} ", serverPort, serviceName);

    Map<String, Object> responseMqp = new HashMap();
    responseMqp.put("method", "response2");
    responseMqp.put("serverPort", serverPort);
    responseMqp.put("serviceName", serviceName);
    responseMqp.put("internalClient", internalClient.response1().toString());
    executorService = Executors.newFixedThreadPool(2);
    executorService.submit(new Worker());
    return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(responseMqp));
  }

  @GetMapping("/error")
  @HystrixCommand(groupKey = "custom-th", commandKey = "custom-cm", fallbackMethod = "breaker")
  public Mono<ResponseEntity<Map<String, Object>>> error() throws Exception {
    log.info(" serverPort : {} serviceName : {} ", serverPort, serviceName);
    throw new RuntimeException("RuntimeException");
  }
}
