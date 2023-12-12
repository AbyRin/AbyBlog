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
    public List<Product> searchProduct(@RequestParam(value="productName", required = false) String productName,
                                       @RequestParam(value="productClass", required = false) String productClass,
                                       @RequestParam(value="minPrice", required = false) Integer minPrice,
                                       @RequestParam(value="maxPrice", required = false) Integer maxPrice) {
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();

        // 按商品名搜索
        if (productName != null && !productName.isEmpty()) {
            queryWrapper.like("product_name", productName);
        }

        // 按商品类别搜索
        if (productClass != null && !productClass.isEmpty()) {
            if (!productClass.equals("All")) {  // 如果不是"All"，则添加条件
                queryWrapper.eq("product_class", productClass);
                System.out.println("productClass: " + productClass);
            }
            // 如果是"All"，则不添加任何条件，即匹配全部
        }

        // 按商品价格区间搜索
        if (minPrice != null && maxPrice != null) {
            queryWrapper.between("product_price", minPrice, maxPrice);
        } else if (minPrice != null) {
            queryWrapper.ge("product_price", minPrice);
        } else if (maxPrice != null) {
            queryWrapper.le("product_price", maxPrice);
        }

        // 如果 productClass 是 "All"，则不添加 product_class 的条件，即匹配全部
        if (productClass != null && productClass.equals("All")) {
            queryWrapper.isNull("product_class");
        }

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