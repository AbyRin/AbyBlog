package com.cpr.abyblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("cart")
public class Cart {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String productId;
    private Integer purchaseNum;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getPurchaseNum() {
        return purchaseNum;
    }

    public void setPurchaseNum(Integer purchaseNum) {
        this.purchaseNum = purchaseNum;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "userId=" + userId +
                ", productId='" + productId + '\'' +
                ", purchaseNum=" + purchaseNum +
                '}';
    }
}
