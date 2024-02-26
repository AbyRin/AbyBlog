package com.cpr.abyblog.service;

import com.cpr.abyblog.entity.Consignee;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aby
 * @since 2024-02-26 01:29:48
 */
public interface ConsigneeService extends IService<Consignee> {

    List<Consignee> getConsigneeListByUserId(Integer userId);
}
