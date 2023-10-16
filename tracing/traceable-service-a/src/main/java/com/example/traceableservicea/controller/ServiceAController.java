package com.example.traceableservicea.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class ServiceAController {

    @PostMapping("/callback/{callbackId}")
    public ResponseEntity<Void> callback(@PathVariable String callbackId) {
        log.info("Received callback: {}", callbackId);
        return ResponseEntity.ok().build();
    }

}
