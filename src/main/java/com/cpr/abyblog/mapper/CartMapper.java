package com.cpr.abyblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpr.abyblog.dto.UserCartDTO;
import com.cpr.abyblog.entity.Cart;
import com.cpr.abyblog.entity.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper extends BaseMapper<Cart> {

    @Select("SELECT * FROM product WHERE product_id = #{productId}")
    Product getProductById(@Param("productId") Integer productId);

    // 连表查询：根据用户ID 查找 购物车
    List<UserCartDTO> getUserCart(@Param("userId") Integer userId);
}