package com.cpr.abyblog.service.impl;

import com.cpr.abyblog.entity.Consignee;
import com.cpr.abyblog.mapper.ConsigneeMapper;
import com.cpr.abyblog.service.ConsigneeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Aby
 * @since 2024-02-26 01:29:48
 */
@Service
public class ConsigneeServiceImpl extends ServiceImpl<ConsigneeMapper, Consignee> implements ConsigneeService {

    @Override
    public List<Consignee> getConsigneeListByUserId(Integer userId) {
        return baseMapper.getConsigneeListByUserId(userId);
    }

}
