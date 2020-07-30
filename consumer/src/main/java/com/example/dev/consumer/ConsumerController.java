package com.example.dev.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    private RestOperations getRestTemplate() {
        return restTemplate;
    }

    @GetMapping("/api/consumer1")
    @HystrixCommand(groupKey = "consumer", commandKey = "consumer1", fallbackMethod = "notConnection")
    public String consumer1() {
        log.debug("Server info {} ", getApiserver().get());
        return getRestTemplate().getForObject("http://gateway/" + getApiserver().get() + "/response1", String.class);
    }

    @GetMapping("/api/consumer2")
    @HystrixCommand(groupKey = "consumer", commandKey = "consumer2", fallbackMethod = "notConnection")
    public String consumer2() {
        return getRestTemplate().getForObject("http://gateway/" + getApiserver().get() + "response2", String.class);
    }

    @GetMapping("/api/error")
    @HystrixCommand(groupKey = "consumer", commandKey = "consumer2", fallbackMethod = "notConnection")
    public String error() {
        return getRestTemplate().getForObject("http://gateway/" + getApiserver().get() + "/error", String.class);
    }

    private Optional<String> getApiserver() {
        List<String> strings = Arrays.asList("api-a", "api-b", "api");
        Optional<String> apiServer = strings.stream().skip((int) (strings.size() * Math.random())).findAny();
        if (apiServer.isPresent()) {
            return apiServer;
        }
        return Optional.of("api-a");
    }

    public String notConnection() {
        return "not Connection!";
    }
}
