package com.example.traceableserviceb.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("ServiceA")
public interface ServiceAClient {

    @PostMapping("/callback/{callbackId}")
    ResponseEntity<Void> callback(@PathVariable String callbackId);
}
