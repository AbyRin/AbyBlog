package com.cpr.cprblog.service;

import com.cpr.cprblog.entity.Cart;
import com.cpr.cprblog.entity.UserCart;
import com.cpr.cprblog.repository.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// week 12 创建CartService类
@Service
public class CartService {
    @Autowired
    private CartDao cartDao;
    public Cart findCart(Cart cart) {
        return cartDao.findCart(cart.getEmail(),cart.getProduct_id());
    }
    public void addCart(Cart cart) {
        cartDao.addCart(cart);
    }
    public List<UserCart> showcart(String email) {
        return cartDao.findByEmail(email);
    }
    public void updateCart(Integer cart_id,Integer num) {
        cartDao.updateCart(cart_id,num);
    }

    // week 12 end

    // week 13
    public void deleteCart(Integer cart_id) {
        cartDao.deleteCart(cart_id);
    }
    public void clearCart(String email) {
        cartDao.clearCart(email);
    }


    // week 15
    public Cart findCartById(int cart_id) {
        return cartDao.findCarById(cart_id);
    }

    public UserCart findByCartID(Integer cart_id) {
        return cartDao.findMyCarById(cart_id);
    }
}


