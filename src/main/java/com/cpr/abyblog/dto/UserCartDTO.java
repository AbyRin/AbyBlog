package com.cpr.abyblog.dto;

import lombok.Data;

@Data
public class UserCartDTO {
    // user
    private Integer userId;
    // product
    private String productId;
    private String productName;
    private Integer productPrice;
    // cart
    private Integer productQuantity;
    private String addTime;
}
