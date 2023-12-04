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
    public List showProduct() {
        List<Product> list = storeMapper.selectList(null);
        System.out.println(list);
        return list;
    }

    // 按名称搜索
    @GetMapping("/store/searchbyname")
    public List searchProductByName() {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("product_name", "%?1%");
        return storeMapper.selectList(queryWrapper);
    }

    // 按类别搜索

    // 按价格区间搜索

    // 复合方式搜索（前三种方式结合）
}