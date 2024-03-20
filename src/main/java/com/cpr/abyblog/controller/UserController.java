package com.cpr.abyblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpr.abyblog.Utils.JwtUtils;  // JWT
import com.cpr.abyblog.common.Constants;
import com.cpr.abyblog.common.Result;
import com.cpr.abyblog.dto.UserDTO;
import com.cpr.abyblog.entity.User;
import com.cpr.abyblog.service.UserService;
import org.jsoup.internal.StringUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

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
    public Result login(@RequestBody UserDTO userDTO) {
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        if (StringUtil.isBlank(email) || StringUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        UserDTO dto = userService.login(userDTO);

        // 如果用户验证通过，生成JWT令牌并返回给客户端
        if (dto != null) {
            int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;
            String token = JwtUtils.generateToken(dto.getEmail(), EXPIRATION_TIME);

            // 将 token 放入返回结果中
            dto.setToken(token);
            return Result.success(dto);
        } else {
            return Result.error(Constants.CODE_401, "用户名或密码错误");
        }
    }

     // 用户注册
    @PostMapping("/signup")
    public Result signup(@RequestBody UserDTO userDTO) {
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        if (StringUtil.isBlank(email) || StringUtil.isBlank(password)) {
            return Result.error(Constants.CODE_400, "参数错误");
        }
        return Result.success(userService.signup(userDTO));
    }

    // 用户登出-已在前端实现
    //    @PostMapping("/logout")
    //    public boolean logout(@RequestBody User user) {
    //        return  userService.logout(user);
    //    }


    // 管理员权限————
    // 添加用户
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user) {
        boolean success = userService.save(user);
        if (success) {
            return Result.success("用户添加成功");
        } else {
            return Result.error(Constants.CODE_500, "用户添加失败");
        }
    }


    // 更新用户信息
    @PostMapping("/updateUser")
    public boolean updateUser(@RequestBody User user) {
        return userService.updateById(user);
    }

    // 分页查询：按条件查询用户
    // 按昵称查询
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