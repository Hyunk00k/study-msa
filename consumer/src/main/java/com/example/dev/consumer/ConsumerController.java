package com.example.dev.consumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@RestController
@Log4j2
public class ConsumerController {

    @Autowired
    RestTemplate restTemplate;

    private RestOperations getRestTemplate() {
        return restTemplate;
    }

    @GetMapping("/consumer1")
    @HystrixCommand(groupKey = "consumer", commandKey = "consumer1", fallbackMethod = "notConnection")

    public String consumer1() {
        return getRestTemplate().getForObject("http://api/response1", String.class);
    }

    @GetMapping("/consumer2")
    @HystrixCommand(groupKey = "consumer", commandKey = "consumer2", fallbackMethod = "notConnection")
    public String consumer2() {
        return getRestTemplate().getForObject("http://api/response2", String.class);
    }

    @GetMapping("/error")
    @HystrixCommand(groupKey = "consumer", commandKey = "consumer2", fallbackMethod = "notConnection")
    public String error() {
        return getRestTemplate().getForObject("http://api/error", String.class);
    }


    public String notConnection() {
        return "not Connection!";
    }
}
