package com.cpr.abyblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("order")
public class Order {
    @TableId(type = IdType.AUTO)
    private Integer order_id;
    private String order_date;
    private Integer user_id;
    private Integer product_id;
}
