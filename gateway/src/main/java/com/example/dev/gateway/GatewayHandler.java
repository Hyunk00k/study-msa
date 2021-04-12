package com.example.dev.gateway;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class GatewayHandler {

    public Mono<ServerResponse> fallback(ServerRequest request) {
        log.info(" fallback : {} ", "Gateway-fallback");

        Map<String, Object> responseMqp = new HashMap();
        responseMqp.put("method", "fallback");
        responseMqp.put("message", "java.net.ConnectException");
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(responseMqp), Object.class);
    }
}
