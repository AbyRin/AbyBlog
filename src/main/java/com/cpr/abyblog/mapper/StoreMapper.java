package com.cpr.abyblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpr.abyblog.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper extends BaseMapper<Product> {

    /* mybatis 代码：已弃用，改为 mybatis-plus */
    /*
        // 商品展示
        @Select("select * from product")
        List<Product> showProduct();

        // 按名称搜索
        @Select("select * from product where product_name like '%?1%' and price between ?2 and ?3 order by product_id")
        List<Product> SearchProductByName();

        // 按类别搜索
        @Select("select * from product where product_class=?1 and price between ?2 and ?3 order by product_id")
        List<Product> SearchProductByClass();

        // 按价格区间搜索
        @Select("select * from product where price between ?1 and ?2 order by product_id")
        public List<Product> SearchProductByPrice();

        // 复合方式搜索（前三种方式结合）
        @Select("select * from product where product_name like '%?1%' and product_class=?2 and price between ?3 and ?4 order by product_id")
        List<Product> SearchProductById();
    */
}