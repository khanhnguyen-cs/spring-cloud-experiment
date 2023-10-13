package com.example.customerservice.feignclient;

import com.example.customerservice.model.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("AccountService")
public interface AccountClient {

    @GetMapping("/accounts")
    List<Account> getAccounts();
}
