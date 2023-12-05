package com.cpr.abyblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cpr.abyblog.entity.Product;
import com.cpr.abyblog.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StoreController {

    @Autowired
    private StoreMapper storeMapper;

    // 商品展示
    @GetMapping("/store")
    public List<Product> showProduct() {
        return storeMapper.selectList(null);
    }

    // 商品搜索
    @GetMapping("/store/search")
    public List<Product> searchProductByName() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("productName", "%?1%");
        return storeMapper.selectList(queryWrapper);
    }
}