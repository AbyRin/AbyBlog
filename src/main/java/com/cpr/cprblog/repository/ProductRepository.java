package com.cpr.cprblog.repository;

import com.cpr.cprblog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    /* 商品展示 */
    @Query(value="select distinct(product_class) from product",nativeQuery = true)
    List<String> findproduct_class();

    /* 商品搜索 */
    @Query(value="select * from product where product_name like '%?1%' and product_class=?2 and price between ?3 and ?4 order by product_id",nativeQuery = true)
    List<Product> searchproduct(String product_name,String product_class,Integer minprice, Integer maxprice);
    @Query(value="select * from product where price between ?1 and ?2 order by product_id",nativeQuery = true)
    List<Product> searchproduct(Integer minPrice, Integer maxPrice);
    @Query(value="select * from product where product_class=?1 and price between ?2 and ?3 order by product_id",nativeQuery = true)
    List<Product> searchproductbyproduct_class(String product_class, Integer minPrice, Integer maxPrice);
    @Query(value="select * from product where product_name like '%?1%' and price between ?2 and ?3 order by product_id",nativeQuery = true)
    List<Product> searchproductbyproduct_name(String product_name, Integer minPrice, Integer maxPrice);
}