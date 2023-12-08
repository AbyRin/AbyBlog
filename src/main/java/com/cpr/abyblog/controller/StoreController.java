package com.cpr.abyblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cpr.abyblog.entity.Product;
import com.cpr.abyblog.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    // 商品展示
    @GetMapping("/showProduct")
    public List<Product> showProduct() {
        return storeService.list();
    }

    // 商品搜索
    @GetMapping("/searchProduct")
    public List<Product> searchProduct(@RequestParam(value="productName", defaultValue = "", required = false) String productName,
                                             @RequestParam(value="productClass", defaultValue = "", required = false) String productClass,
                                             @RequestParam(value="minPrice", required = false) Integer minPrice,
                                             @RequestParam(value="maxPrice", required = false) Integer maxPrice) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("product_name", productName);  // 按商品名搜索
        queryWrapper.eq("product_class", productClass);  // 按商品类别搜索
        queryWrapper.between("product_price", minPrice, maxPrice);  // 按商品价格区间搜索
        return storeService.list(queryWrapper);
    }

    // 商品添加
    @PostMapping("/addProduct")
    public boolean addProduct(@RequestBody Product product) {
        return storeService.save(product);
    }

    // 商品更新
    @PutMapping("/updateProduct")
    public boolean updateProduct(@RequestBody Product product) {
        return storeService.updateById(product);
    }

    // 商品删除
    @DeleteMapping("/deleteProduct")
    public boolean deleteProduct(@RequestParam Integer productId) {
        return storeService.removeById(productId);
    }
}