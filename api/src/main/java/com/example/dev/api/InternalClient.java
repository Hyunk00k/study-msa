package com.example.dev.api;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "api-DB")
@Headers({"Content-type", "application/json"})
public interface InternalClient {
  @RequestMapping(method = RequestMethod.GET, value = "/internal/response1")
  feign.Response response1();

  @RequestMapping(method = RequestMethod.GET, value = "/internal/response2")
  feign.Response response2();
}
