package com.cpr.abyblog.controller;

import com.cpr.abyblog.entity.User;
import com.cpr.abyblog.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {

    @Autowired
    private AccountMapper accountMapper;

    // 展示用户信息
    @GetMapping("/account")
    public List<User> showAccount() {
        return accountMapper.selectList(null);
    }
}