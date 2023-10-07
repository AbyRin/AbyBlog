package com.cpr.cprblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InspirationController {

    @RequestMapping("/inspiration")
    public String toInspiration() {

        return "inspiration";
    }
}