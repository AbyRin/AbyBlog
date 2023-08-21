package com.aby.abyblog.service;

import com.aby.abyblog.repository.ProductRepository;
import com.aby.abyblog.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public List<String> findclass() {
        return productRepository.findproductclass();
    }

    public Product findById(String productid) {
        return productRepository.findById(productid).get();
    }
    /* 用于: 客户评价评价 和 后台修改上架商品 */

    public List<Product> searchproduct(String productname, String productclass, Integer minprice, Integer maxprice) {
        return productRepository.searchproduct(productname,productclass,minprice,maxprice);
    }

    public List<Product> searchproduct(Integer minPrice, Integer maxPrice) {
        return productRepository.searchproduct(minPrice,maxPrice);
    }

    public List<Product> searchproductbyfname(String productname, Integer minPrice, Integer maxPrice) {
        return productRepository.searchproductbyproductname(productname,minPrice,maxPrice);
    }

    public List<Product> searchproductbyfclass(String productclass, Integer minPrice, Integer maxPrice) {
        return productRepository.searchproductbyproductclass(productclass,minPrice,maxPrice);
    }
}