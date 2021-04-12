package com.example.dev.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
@Configuration
@EnableFeignClients
public class ApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(ApiApplication.class, args);
  }

  @Bean
  RouterFunction routes(ApiHandler apiHandler) {
    return RouterFunctions.route(
            GET("/handler1").and(accept(MediaType.APPLICATION_JSON)), apiHandler::handler1)
        .andRoute(GET("/handler2").and(accept(MediaType.APPLICATION_JSON)), apiHandler::handler2);
  }
}
