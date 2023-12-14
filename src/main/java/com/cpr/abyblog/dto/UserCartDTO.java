package com.cpr.abyblog.dto;

import lombok.Data;

@Data
public class UserCartDTO {
    // user
    private Integer userId;
    // product
    private Integer productId;
    private String productName;
    private String productClass;
    private Integer productPrice;
    private String productPicture;
    private Integer purchaseLimit;
    // cart
    private Integer productQuantity;
    private String addTime;
}
