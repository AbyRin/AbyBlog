package com.cpr.abyblog.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpr.abyblog.controller.dto.UserDTO;
import com.cpr.abyblog.entity.User;
import com.cpr.abyblog.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper, User> {

//    @Override
//    public boolean login(UserDTO, userDTO) {
//        return false;
//    }
}
