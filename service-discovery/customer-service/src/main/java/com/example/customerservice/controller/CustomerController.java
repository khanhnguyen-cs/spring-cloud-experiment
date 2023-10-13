package com.example.customerservice.controller;

import com.example.customerservice.feignclient.AccountClient;
import com.example.customerservice.model.Account;
import com.example.customerservice.model.Customer;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Log4j2
@RestController
public class CustomerController {

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    private AccountClient accountClient;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        log.debug("Handled get customers from: {}", serviceName);
        List<Account> accounts = accountClient.getAccounts();
        return Arrays.asList(
                Customer.builder().firstName("Khanh").lastName("Nguyen").accounts(accounts).build()
        );
    }
}
