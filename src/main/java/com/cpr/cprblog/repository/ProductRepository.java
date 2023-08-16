package com.cpr.cprblog.repository;

import com.cpr.cprblog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product,String> {

    /* 商品展示 */
    @Query(value="select distinct(productclass) from product",nativeQuery = true)
    List<String> findproductclass();

    /* 商品搜索 */
    @Query(value="select * from product where productname like '%?1%' and productclass=?2 and price between ?3 and ?4 order by productid",nativeQuery = true)
    List<Product> searchproduct(String productname,String productclass,Integer minprice, Integer maxprice);
    @Query(value="select * from product where price between ?1 and ?2 order by productid",nativeQuery = true)
    List<Product> searchproduct(Integer minPrice, Integer maxPrice);
    @Query(value="select * from product where productclass=?1 and price between ?2 and ?3 order by productid",nativeQuery = true)
    List<Product> searchproductbyproductclass(String productclass, Integer minPrice, Integer maxPrice);
    @Query(value="select * from product where productname like '%?1%' and price between ?2 and ?3 order by productid",nativeQuery = true)
    List<Product> searchproductbyproductname(String productname, Integer minPrice, Integer maxPrice);
}