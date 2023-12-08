package com.cpr.abyblog.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpr.abyblog.entity.Product;
import com.cpr.abyblog.mapper.StoreMapper;
import org.springframework.stereotype.Service;

@Service
public class StoreService extends ServiceImpl<StoreMapper, Product> {

}
