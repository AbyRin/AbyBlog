package com.cpr.abyblog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cpr.abyblog.common.Constants;
import com.cpr.abyblog.dto.UserDTO;
import com.cpr.abyblog.entity.User;
import com.cpr.abyblog.exception.ServiceException;
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

    private static final Log LOG = Log.get();

    // 用户登录
    @Override
    public UserDTO login(UserDTO userDTO) {
        User oneUser = getUserInfo(userDTO);
        // 注意异常抛出顺序的先后
        if (oneUser != null) {
            BeanUtil.copyProperties(oneUser, userDTO, true);
            // 注意：mybatis-plus 的 getOne() 获取数据库中第一条数据，如果数据库中有脏数据，就会导致错误
            // 将查询到 user 中字段的值 赋给 userDTO，提供给前端
            return userDTO;
        } else {
            throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
        }
    }

    // 用户注册
    @Override
    public User signup(UserDTO userDTO) {
        try {
            User newUser = getUserInfo(userDTO);
            if (newUser == null) {
                newUser = new User();
                BeanUtil.copyProperties(userDTO, newUser, true);
                save(newUser);  // 将复制后的用户对象保存到数据库
            } else {
                throw new ServiceException(Constants.CODE_600, "用户已存在");
            }
            return newUser;
        } catch (Exception e) {
            LOG.error("用户注册时发生错误", e);
            throw new ServiceException(Constants.CODE_500, "内部服务器错误");
        }
    }

    // 通用部分：获取前端传来的用户信息
    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userDTO.getEmail());
        queryWrapper.eq("password", userDTO.getPassword());

        User oneUser;
        try {
            oneUser = getOne(queryWrapper);
        } catch(Exception e){
            LOG.error(e);
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return oneUser;
    }
}