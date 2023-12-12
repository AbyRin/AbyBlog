package com.cpr.abyblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpr.abyblog.Utils.JwtUtils;
import com.cpr.abyblog.Utils.Result;
import com.cpr.abyblog.dto.UserDTO;
import com.cpr.abyblog.entity.User;
import com.cpr.abyblog.service.UserService;
import org.jsoup.internal.StringUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Aby
 * @since 2023-12-12 04:53:40
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    // 展示用户名单
    @GetMapping("/showUser")
    public List<User> showUser() {
        return userService.list();
    }

    // 用户登录
    @PostMapping("/login")
    @ResponseBody
    public boolean login(@RequestBody UserDTO userDTO) {
        String account = userDTO.getAccount();
        String password = userDTO.getPassword();
        if (StringUtil.isBlank(account) || StringUtil.isBlank(password)) {
            return false;
        }
        return userService.login(userDTO);
    }

    // 用户注册
//    @PostMapping("/signup")
//    public boolean signup(@RequestBody User user) {
//        return  userService.signup(user);
//    }

    // 用户登出
//    @PostMapping("/logout")
//    public boolean logout(@RequestBody User user) {
//        return  userService.logout(user);
//    }


    // 管理员权限————
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

    // 分页查询：按条件查询用户
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