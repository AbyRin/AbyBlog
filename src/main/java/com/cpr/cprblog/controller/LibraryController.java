package com.cpr.cprblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LibraryController {

    @RequestMapping("/library")
    public String toLibrary() {

        return "library";
    }
}