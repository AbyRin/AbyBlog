package com.cpr.abyblog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    @RequestMapping("/account")
    public String toAccount() {

        return "account";
    }
}