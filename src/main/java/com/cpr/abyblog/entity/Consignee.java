package com.cpr.abyblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("consignee")
public class Consignee {

    @TableId(value = "consignee_id", type = IdType.AUTO)
    private Integer consigneeId;

    private Integer userId;

    private String consigneeName;

    private String consigneeMobile;

    private String consigneeProvince;

    private String consigneeCity;

    private String consigneeArea;

    private String consigneeAddress;
}
