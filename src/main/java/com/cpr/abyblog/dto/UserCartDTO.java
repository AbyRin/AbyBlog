package com.cpr.abyblog.dto;

import lombok.Data;

@Data
public class UserCartDTO {
    // user 表
    private Integer userId;

    // product 表
    private Integer productId;
    private String productName;
    private String productClass;
    private Integer productPrice;
    private String productPicture;
    private Integer purchaseLimit;

    // cart 表
    private Integer productQuantity;
    private String addTime;
}
