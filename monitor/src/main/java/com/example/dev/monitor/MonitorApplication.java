package com.example.dev.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
@EnableHystrixDashboard
@EnableDiscoveryClient
public class MonitorApplication {

  public static void main(String[] args) {
    SpringApplication.run(MonitorApplication.class, args);
  }
}
