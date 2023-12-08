package com.cpr.abyblog.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpr.abyblog.entity.Cart;
import com.cpr.abyblog.mapper.CartMapper;
import org.springframework.stereotype.Service;

@Service
public class CartService extends ServiceImpl<CartMapper, Cart> {
}
