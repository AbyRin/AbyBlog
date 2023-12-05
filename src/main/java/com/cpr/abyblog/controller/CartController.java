package com.cpr.abyblog.controller;

import com.cpr.abyblog.entity.Cart;
import com.cpr.abyblog.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private CartMapper cartMapper;

    // 展示购物车
    @GetMapping("/cart")
    public List<Cart> showCart() {
        return cartMapper.selectList(null);
    }

    // 加入购物车
    @PostMapping("/cart/add")
    public String addCart(Integer productId) {
        Cart cart = new Cart();
        cart.setProductId(String.valueOf(productId));

        int i = cartMapper.insert(cart);
        if (i > 0) {
            return "购物车添加成功";
        } else {
            return "购物车添加失败";
        }
    }

    // 删除商品
    @DeleteMapping("/cart/deleteCart")
    public String deleteCart(Integer productId) {
        int i = cartMapper.deleteById(productId);
        if (i > 0) {
            return "购物车删除成功";
        } else {
            return "购物车删除失败";
        }
    }
}
