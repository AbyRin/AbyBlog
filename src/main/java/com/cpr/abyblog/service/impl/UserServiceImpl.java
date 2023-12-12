package com.cpr.abyblog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cpr.abyblog.dto.UserDTO;
import com.cpr.abyblog.entity.User;
import com.cpr.abyblog.mapper.UserMapper;
import com.cpr.abyblog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Aby
 * @since 2023-12-12 04:53:40
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public boolean login(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userDTO.getAccount());
        queryWrapper.eq("password", userDTO.getPassword());
        User one = getOne(queryWrapper);
        return one != null;
    }
}