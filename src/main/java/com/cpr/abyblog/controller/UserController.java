package com.cpr.abyblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpr.abyblog.controller.dto.UserDTO;
import com.cpr.abyblog.entity.User;
import com.cpr.abyblog.Utils.JwtUtils;
import com.cpr.abyblog.Utils.Result;
import com.cpr.abyblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    // 展示用户名单
    @GetMapping("/showUser")
    public List<User> showUser() {
        return userService.list();
    }

    // 用户登录
//    @PostMapping("/login")
//    public Result signIn(@RequestBody User user) {
//        String token = JwtUtils.generateToken(user.getEmail());
//        Map<String, Object> data = new HashMap<>();
//        data.put("token", token);
//        return Result.pass().data(data);
//    }

    // 添加用户
    @PostMapping("/addUser")
    public boolean addUser(@RequestBody User user) {
        return userService.save(user);
    }

    // 更新用户信息
    @PostMapping("/updateUser")
    public boolean updateUser(@RequestBody User user) {
        return userService.updateById(user);
    }

    // 用户登录
//    @PostMapping("/login")
//    public Result signIn(@RequestBody UserDTO userDTO) {
//       String account = userDTO.getEmail();
//       String password = userDTO.getPassword();
//       return userService.login(userDTO)
//    }

    // 用户注册
    @PostMapping("/signup")

    // 查询数据库，返回用户信息
    @GetMapping("/info")
    public Result info(String token) {
        String nickname = JwtUtils.getClaimsByToken(token).getSubject();
        String url = "./Asuka.jpg";
        Map<String, Object> data = new HashMap<>();
        data.put("name", nickname);
        data.put("avatar", url);
        return Result.pass().data(data);
    }

    @PostMapping("/logout")
    public Result signOut() {
        return Result.pass();
    }

    // 分页查询（mybatis-plus）
    @GetMapping("/pageUser")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(value="nickName", required = false) String nickName) {
        IPage<User> page = new Page<>(pageNum, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("nick_name", nickName);
        return userService.page(page, queryWrapper);
    }
}
