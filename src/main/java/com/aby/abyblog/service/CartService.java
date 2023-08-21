package com.aby.abyblog.service;

import com.aby.abyblog.entity.Cart;
import com.aby.abyblog.entity.MyCart;
import com.aby.abyblog.repository.CartDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// week 12 创建CartService类
@Service
public class CartService {
    @Autowired
    private CartDao cartDao;
    public Cart findCart(Cart cart) {
            return cartDao.findCart(cart.getEmail(),cart.getProductid());
    }
    public void addCart(Cart cart) {
        cartDao.addCart(cart);
    }
    public List<MyCart> showcart(String email) {
        return cartDao.findByEmail(email);
    }
    public void updateCart(Integer cartId,Integer num) {
        cartDao.updateCart(cartId,num);
    }

    // week 12 end

    // week 13
    public void deleteCart(Integer cartId) {
        cartDao.deleteCart(cartId);
    }
    public void clearCart(String email) {
        cartDao.clearCart(email);
    }


    // week 15
    public Cart findCartById(int cart_id) {
        return cartDao.findCarById(cart_id);
    }

    public MyCart findByCartID(Integer cartID) {
        return cartDao.findMyCarById(cartID);
    }
}

