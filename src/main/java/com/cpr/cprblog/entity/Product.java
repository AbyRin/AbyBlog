package com.cpr.cprblog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="product")
public class Product {
    @Id
    private String product_id;
    private String product_name;
    private String product_class;
    private String illustrate;
    private Integer price;
    private String picture;
    private Integer soldnum;

    // 生成setter和getter方法
    public String getProductid() {
        return product_id;
    }

    public void setProductid(String product_id) {
        this.product_id = product_id;
    }

    public String getProductname() {
        return product_name;
    }

    public void setProductname(String product_name) {
        this.product_name = product_name;
    }

    public String getProductclass() {
        return product_class;
    }

    public void setProductclass(String product_class) {
        this.product_class = product_class;
    }

    public String getIllustrate() {
        return illustrate;
    }

    public void setIllustrate(String illustrate) {
        this.illustrate = illustrate;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getSoldnum() {
        return soldnum;
    }

    public void setSoldnum(Integer soldnum) {
        this.soldnum = soldnum;
    }
}
