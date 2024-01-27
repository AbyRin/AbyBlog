package com.cpr.abyblog.service;

import com.cpr.abyblog.dto.UserDTO;
import com.cpr.abyblog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aby
 * @since 2023-12-12 04:53:40
 */
public interface UserService extends IService<User> {

    // 用户登录
    UserDTO login(UserDTO userDTO);

    // 用户注册
    User signup(UserDTO userDTO);
}
