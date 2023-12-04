package com.cpr.abyblog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

    @RequestMapping("/library")
    public String toLibrary() {

        return "library";
    }
}