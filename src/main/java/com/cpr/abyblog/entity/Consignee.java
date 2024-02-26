package com.cpr.abyblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aby
 * @since 2024-02-26 01:29:48
 */
@Getter
@Setter
public class Consignee implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "consignee_id", type = IdType.AUTO)
    private Integer consigneeId;

    /**
     * 外键-用户ID，每个用户可以创建三个地址
     */
    private Integer userId;

    /**
     * 收件人-姓名
     */
    private String consigneeName;

    /**
     * 收件人-手机号码
     */
    private String consigneeMobile;

    /**
     * 收件人-省
     */
    private String consigneeProvince;

    /**
     * 收件人-市
     */
    private String consigneeCity;

    /**
     * 收件人-区
     */
    private String consigneeArea;

    /**
     * 收件人-详细地址
     */
    private String consigneeAddress;
}
