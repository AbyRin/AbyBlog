package com.cpr.abyblog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpr.abyblog.entity.Cart;
import com.cpr.abyblog.entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface CartService extends IService<Cart> {

    Product getProductById(Integer productId);
}