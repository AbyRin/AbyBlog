package com.cpr.abyblog.service;

import com.cpr.abyblog.entity.Product;
import com.cpr.abyblog.repository.ProductRepository;
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
        return productRepository.findproduct_class();
    }

    public Product findById(String product_id) {
        return productRepository.findById(product_id).get();
    }
    /* 用于: 客户评价评价 和 后台修改上架商品 */

    public List<Product> searchproduct(String product_name, String product_class, Integer minprice, Integer maxprice) {
        return productRepository.searchproduct(product_name,product_class,minprice,maxprice);
    }

    public List<Product> searchproduct(Integer minPrice, Integer maxPrice) {
        return productRepository.searchproduct(minPrice,maxPrice);
    }

    public List<Product> searchproductbyfname(String product_name, Integer minPrice, Integer maxPrice) {
        return productRepository.searchproductbyproduct_name(product_name,minPrice,maxPrice);
    }

    public List<Product> searchproductbyfclass(String product_class, Integer minPrice, Integer maxPrice) {
        return productRepository.searchproductbyproduct_class(product_class,minPrice,maxPrice);
    }
}