package com.cpr.abyblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpr.abyblog.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}