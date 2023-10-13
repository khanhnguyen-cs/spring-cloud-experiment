package com.example.accountservice.controller;

import com.example.accountservice.model.Account;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Log4j2
@RestController
public class AccountController {

    @Value("${spring.application.name}")
    private String serviceName;

    @GetMapping("/accounts")
    public List<Account> getAccounts() {
        log.debug("Handled get accounts from: {}", serviceName);
        return Arrays.asList(
                Account.builder().id(serviceName + ":account:1").name("Account 1").build(),
                Account.builder().id(serviceName + ":account:2").name("Account 2").build(),
                Account.builder().id(serviceName + ":account:3").name("Account 3").build());
    }
}
