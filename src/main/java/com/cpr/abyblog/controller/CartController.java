package com.cpr.abyblog.controller;

import com.cpr.abyblog.entity.Cart;
import com.cpr.abyblog.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // 展示购物车
    @GetMapping("/showCart")
    public List<Cart> showCart() {
        return cartService.list();
    }

    // 加入购物车
    @PostMapping("/addCart")
    public boolean addCart(Integer productId) {
        Cart cart = new Cart();
        cart.setProductId(String.valueOf(productId));
        return cartService.save(cart);
    }

    // 删除商品
    @DeleteMapping("/deleteCart")
    public boolean deleteCart(Integer productId) {
        return cartService.removeById(productId);
    }
}
