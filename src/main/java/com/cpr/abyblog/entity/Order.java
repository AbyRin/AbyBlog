package com.cpr.abyblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer orderId;
    private String orderDate;
    private Integer userId;
    private Integer productId;
}
