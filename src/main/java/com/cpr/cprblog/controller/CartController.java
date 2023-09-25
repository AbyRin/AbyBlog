package com.cpr.cprblog.controller;

import com.cpr.cprblog.entity.Cart;
import com.cpr.cprblog.entity.UserCart;
import com.cpr.cprblog.entity.Member;
import com.cpr.cprblog.service.CartService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// 完成添加购物车功能和购物车显示功能
@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    // 加入购物车
    @RequestMapping("/cart/addcart")
    public String addcart(String product_id,String picture,HttpSession session){
        Member member=(Member)session.getAttribute("member");
        if(member == null){
            return "forward:/index/toLogin";
        }
        Cart cart = new Cart();
        cart.setEmail(member.getEmail());
        cart.setProduct_id(product_id);
        cart.setNum(1);
        Cart _cart=cartService.findCart(cart);
        if(_cart==null){
            cartService.addCart(cart);
        }else{
            _cart.setNum(_cart.getNum()+1);
            cartService.updateCart(_cart.getCart_id(),_cart.getNum());
        }
        return "redirect:/cart/showcart";  // redirect: URL重定向
    }

    // 显示购物车
    @RequestMapping("/cart/showcart")
    public String showcart(HttpSession session, Model model){
        Member member=(Member)session.getAttribute("member");
        if(member == null){
            return "forward:/index/toLogin";
        }
        List<UserCart> carts=cartService.showcart(member.getEmail());
        model.addAttribute("carts",carts);
        return "showcart";
    }

    // 实现修改购物车数量功能
    @RequestMapping("cart/updateCart")
    public String updateCart(Integer cart_id,Integer num,HttpSession session){
        Cart cart = new Cart();
        cart.setCart_id(cart_id);
        cart.setNum(num);
        // 原: cartService.updateCart(cart);
        cartService.updateCart(cart_id, num);
        return "redirect:/cart/showcart";
    }

    // 实现删除购物车功能
    @RequestMapping("/cart/deleteCart")
    public String deleteCart(Integer cart_id){
        cartService.deleteCart(cart_id);
        return "redirect:/cart/showcart";
    }

    // 实现清除购物车功能
    @RequestMapping("cart/clearCart")
    public String clearCart(HttpSession session){
        Member member=(Member)session.getAttribute("member");
        if(member == null){
            return "forward:/index/toLogin";
        }
        cartService.clearCart(member.getEmail());
        return "redirect:/cart/showcart";
    }
}