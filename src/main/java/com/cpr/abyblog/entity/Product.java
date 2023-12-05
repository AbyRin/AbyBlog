package com.cpr.abyblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("product")
public class Product {
    @TableId(type = IdType.AUTO)
    private Integer productId;
    private String productName;
    private String productClass;
    private String productIllustrate;
    private Integer productPrice;
    private String productPicture;
    private Integer productCollect;
    private Integer productSoldNum;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductClass() {
        return productClass;
    }

    public void setProductClass(String productClass) {
        this.productClass = productClass;
    }

    public String getProductIllustrate() {
        return productIllustrate;
    }

    public void setProductIllustrate(String productIllustrate) {
        this.productIllustrate = productIllustrate;
    }

    public Integer getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Integer productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductPicture() {
        return productPicture;
    }

    public void setProductPicture(String productPicture) {
        this.productPicture = productPicture;
    }

    public Integer getProductCollect() {
        return productCollect;
    }

    public void setProductCollect(Integer productCollect) {
        this.productCollect = productCollect;
    }

    public Integer getProductSoldNum() {
        return productSoldNum;
    }

    public void setProductSoldNum(Integer productSoldNum) {
        this.productSoldNum = productSoldNum;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productClass='" + productClass + '\'' +
                ", productIllustrate='" + productIllustrate + '\'' +
                ", productPrice=" + productPrice +
                ", productPicture='" + productPicture + '\'' +
                ", productCollect=" + productCollect +
                ", productSoldNum=" + productSoldNum +
                '}';
    }
}