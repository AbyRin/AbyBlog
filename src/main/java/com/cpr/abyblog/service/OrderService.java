package com.cpr.abyblog.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpr.abyblog.entity.Order;
import com.cpr.abyblog.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends ServiceImpl<OrderMapper, Order> {
}
