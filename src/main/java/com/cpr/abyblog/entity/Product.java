package com.cpr.abyblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer product_id;
    private String product_name;
    private String product_class;
    private String product_illustrate;
    private Integer product_price;
    private String product_picture;
    private Integer product_collect;
    private Integer product_sold_num;

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_class() {
        return product_class;
    }

    public void setProduct_class(String product_class) {
        this.product_class = product_class;
    }

    public String getProduct_illustrate() {
        return product_illustrate;
    }

    public void setProduct_illustrate(String product_illustrate) {
        this.product_illustrate = product_illustrate;
    }

    public Integer getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Integer product_price) {
        this.product_price = product_price;
    }

    public String getProduct_picture() {
        return product_picture;
    }

    public void setProduct_picture(String product_picture) {
        this.product_picture = product_picture;
    }

    public Integer getProduct_collect() {
        return product_collect;
    }

    public void setProduct_collect(Integer product_collect) {
        this.product_collect = product_collect;
    }

    public Integer getProduct_sold_num() {
        return product_sold_num;
    }

    public void setProduct_sold_num(Integer product_sold_num) {
        this.product_sold_num = product_sold_num;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_class='" + product_class + '\'' +
                ", product_illustrate='" + product_illustrate + '\'' +
                ", product_price=" + product_price +
                ", product_picture='" + product_picture + '\'' +
                ", product_collect=" + product_collect +
                ", product_sold_num=" + product_sold_num +
                '}';
    }
}