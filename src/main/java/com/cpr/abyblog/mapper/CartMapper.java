package com.cpr.abyblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpr.abyblog.entity.Cart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {
}