package com.cpr.abyblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("customer")
public class Customer {

    @TableId(value = "customer_id", type = IdType.AUTO)
    private Integer customerId;

    private String userId;

    private String customerName;

    private String customerMobile;

    private String customerProvince;

    private String customerCity;

    private String customerArea;

    private String customerAddress;

    private String customerDefault;
}
