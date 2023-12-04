package com.cpr.abyblog.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForumController {

    @RequestMapping("/forum")
    public String toForum() {

        return "forum";
    }
}