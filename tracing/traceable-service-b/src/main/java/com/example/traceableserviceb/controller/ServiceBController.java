package com.example.traceableserviceb.controller;

import com.example.traceableserviceb.feignclient.ServiceAClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.Executors;

@Log4j2
@RestController
public class ServiceBController {

    @Autowired
    ServiceAClient serviceAClient;

    @ResponseBody
    @PostMapping("start")
    public String startProcess() {
        String processId = UUID.randomUUID().toString();
        log.info("Started new process with id = {}", processId);
        Executors.newCachedThreadPool().execute(() -> {
            try {
                Thread.sleep(10000);
                serviceAClient.callback(processId);
                log.info("Called back to requested client, callbackId = {}", processId);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return processId;
    }
}
