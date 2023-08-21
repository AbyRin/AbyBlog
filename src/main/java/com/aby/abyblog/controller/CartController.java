package com.aby.abyblog.controller;

import com.aby.abyblog.service.CartService;
import com.aby.abyblog.entity.Cart;
import com.aby.abyblog.entity.MyCart;
import com.aby.abyblog.entity.Member;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

// week 12 完成添加购物车功能和购物车显示功能
@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    // 加入购物车
    @RequestMapping("/cart/addcart")
    public String addcart(String productid,String pictures,HttpSession session){
        Member member=(Member)session.getAttribute("member");
        if(member == null){
            return "forward:/index/toLogin";
        }
        Cart cart = new Cart();
        cart.setEmail(member.getEmail());
        cart.setProductid(productid);
        cart.setNum(1);
        Cart _cart=cartService.findCart(cart);
        if(_cart==null){
            cartService.addCart(cart);
        }else{
            _cart.setNum(_cart.getNum()+1);
            cartService.updateCart(_cart.getCartId(),_cart.getNum());
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
        List<MyCart> carts=cartService.showcart(member.getEmail());
        model.addAttribute("carts",carts);
        return "showcart";
    }
    // week 12 end

    // week 13
    // 实现修改购物车数量功能
    @RequestMapping("cart/updateCart")
    public String updateCart(Integer cartId,Integer num,HttpSession session){
        Cart cart = new Cart();
        cart.setCartId(cartId);
        cart.setNum(num);
        // 原: cartService.updateCart(cart);
        cartService.updateCart(cartId, num);
        return "redirect:/cart/showcart";
    }

    // 实现删除购物车功能
    @RequestMapping("/cart/deleteCart")
    public String deleteCart(Integer cartId){
        cartService.deleteCart(cartId);
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
