package com.cpr.abyblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cpr.abyblog.dto.UserCartDTO;
import com.cpr.abyblog.entity.Cart;
import com.cpr.abyblog.entity.Product;
import com.cpr.abyblog.mapper.CartMapper;
import com.cpr.abyblog.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private CartService cartService;

    // 展示购物车
    @GetMapping("/showCart")
    public List<UserCartDTO> showCart(@RequestParam Integer userId) {
        return cartMapper.getUserCart(userId);
    }

    // 更新购物车
    @PostMapping("/updateCart")
    public ResponseEntity<Integer> updateCart(@RequestParam(value="userId") Integer userId,
                              @RequestParam(value="productId") Integer productId,
                              @RequestParam(value="purchaseQuantity") Integer purchaseQuantity) {

        // 使用查询构造器查询购物车中是否已存在该商品
        QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("product_id", productId);

        // 唯一标识：一位用户的购物车的一种商品
        Cart existingCart = cartService.getOne(queryWrapper);

        // 查询：购物车中是否存在该商品？ ->
        if (existingCart != null) {  // 存在
            // 查询：商品限购额
            Product product = cartService.getProductById(productId);
            Integer purchaseLimit = product.getPurchaseLimit();

            // 计算更新后的商品数量
            int updatedQuantity = existingCart.getProductQuantity() + purchaseQuantity;

            // 检查购买数量是否在限制范围内？ ->
            if (updatedQuantity >= 0 && updatedQuantity <= purchaseLimit) {
                UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("user_id", userId);
                updateWrapper.eq("product_id", productId);
                updateWrapper.set("product_quantity", updatedQuantity);

                cartService.update(updateWrapper);
                return new ResponseEntity<>(1001, HttpStatus.OK);  // 状态码1001：更新购物车-成功(商品存在，修改数量)
            } else {
                // 购买数量超过限制或者商品不存在，不允许更新购物车中的数量
                return new ResponseEntity<>(2001, HttpStatus.BAD_REQUEST);  // 状态码2001：达到限购额
            }
        } else {
            // 购物车中不存在该商品：添加一个 Cart 类，设置购买数量为 1 或者传入的 purchaseQuantity
            Cart newCart = new Cart();
            newCart.setUserId(userId);
            newCart.setProductId(productId);
            newCart.setProductQuantity(Math.max(1, purchaseQuantity));

            cartService.save(newCart);
            return new ResponseEntity<>(1000, HttpStatus.CREATED);  // 状态码1000：加入购物车-成功(商品不存在，新加购物车)
        }
    }

    // 购物车：删除商品
    @DeleteMapping("/deleteCart")
    public int deleteCart(@RequestParam(value = "userId") Integer userId,
                          @RequestParam(value = "productId") Integer productId) {
        Map<String, Object> columnMap = new HashMap<>();
        columnMap.put("user_id", userId);
        columnMap.put("product_id", productId);
        return cartMapper.deleteByMap(columnMap);
    }
}
