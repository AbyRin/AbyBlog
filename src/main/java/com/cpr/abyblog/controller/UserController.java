package com.cpr.abyblog.controller;

import com.cpr.abyblog.entity.User;
import com.cpr.abyblog.common.JwtUtils;
import com.cpr.abyblog.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @PostMapping("/signin")
    public Result signIn(@RequestBody User user) {
        String token = JwtUtils.generateToken(user.getEmail());
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        return Result.pass().data(data);
    }

    @GetMapping("/info")
    public Result info(String token) {
        String nickname = JwtUtils.getClaimsByToken(token).getSubject();
        String url = "./Asuka.jpg";
        Map<String, Object> data = new HashMap<>();
        data.put("name", nickname);
        data.put("avatar", url);
        return Result.pass().data(data);
    }


    @PostMapping("/signout")
    public Result signOut() {
        return Result.pass();
    }
}
