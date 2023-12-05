package com.cpr.abyblog.controller;

import com.cpr.abyblog.entity.Product;
import com.cpr.abyblog.mapper.ManageStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ManageStoreController {

    @Autowired
    private ManageStoreMapper manageStoreMapper;

    // 商品展示
    @GetMapping("/manageStore")
    public List<Product> showProduct() {
        return manageStoreMapper.selectList(null);
    }

    // 商品添加
    @PostMapping("/manageStore/add")
    public String addProduct(Product product) {
        int i = manageStoreMapper.insert(product);
        if (i > 0) {
            return "添加成功";
        } else {
            return "添加失败";
        }
    }

    // 更新商品
    @PutMapping("/manageStore/update")
    public String updateProduct(Product product) {
        int i = manageStoreMapper.updateById(product);
        if (i > 0) {
            return "更新成功";
        } else {
            return "更新失败";
        }
    }

    // 删除商品
    @DeleteMapping("/manageStore/delete")
    public String deleteProduct(Integer productId) {
        int i = manageStoreMapper.deleteById(productId);
        if (i > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}
