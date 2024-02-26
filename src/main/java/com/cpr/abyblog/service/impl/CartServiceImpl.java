package com.cpr.abyblog.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpr.abyblog.entity.Cart;
import com.cpr.abyblog.entity.Product;
import com.cpr.abyblog.mapper.CartMapper;
import com.cpr.abyblog.service.CartService;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

    @Override
    public Product getProductById(Integer productId) {
        return baseMapper.getProductById(productId);
    }
}