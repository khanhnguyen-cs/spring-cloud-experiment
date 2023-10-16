package com.example.traceableservicea.feignclient;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("ServiceB")
public interface ServiceBClient {

    @PostMapping("start")
    String startProcess();
}
