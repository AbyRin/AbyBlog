package com.cpr.abyblog.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


class ApiResponse{
    private int code;
    private Map<String, String> data;

    public ApiResponse(int code,Map<String, String> data){
        this.code = code;
        this.data = data;
    }

    public int getCode(){
        return this.code;
    }

    public Map<String, String> getData(){
        return this.data;
    }
}

@RestController
@RequestMapping("/api")
public class DemoController {

    @RequestMapping("users")
    @ResponseBody
    public ApiResponse User(){
        Map<String, String> data = new HashMap<>();
        data.put("admin", "123456");
        return new ApiResponse(200, data);
    }
}
