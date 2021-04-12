package com.example.dev.api;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Log4j2
@Component
public class ApiHandler {

  @Value("${server.port}")
  private String serverPort;

  @Value("${service.name}")
  private String serviceName;

  private ExecutorService executorService;

  public Mono<ServerResponse> handler1(ServerRequest request) {
    log.info(" serverPort : {} serviceName : {} ", serverPort, serviceName);

    Map<String, Object> responseMqp = new HashMap();
    responseMqp.put("method", "handler1");
    responseMqp.put("serverPort", serverPort);
    responseMqp.put("serviceName", serviceName);
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(responseMqp), Object.class);
  }

  public Mono<ServerResponse> handler2(ServerRequest request) {
    log.info(" serverPort : {} serviceName : {} ", serverPort, serviceName);

    Map<String, Object> responseMqp = new HashMap();
    responseMqp.put("method", "handler2");
    responseMqp.put("serverPort", serverPort);
    responseMqp.put("serviceName", serviceName);
    executorService = Executors.newFixedThreadPool(2);
    executorService.submit(new Worker());
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(Mono.just(responseMqp), Object.class);
  }

  public Mono<ServerResponse> error2(ServerRequest request) throws Exception {
    log.info(" serverPort : {} serviceName : {} ", serverPort, serviceName);
    throw new RuntimeException("RuntimeException");
  }
}
