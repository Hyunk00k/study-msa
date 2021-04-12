package com.example.dev.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Configuration;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
@Configuration
public class ApiDBApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDBApplication.class, args);
    }

}