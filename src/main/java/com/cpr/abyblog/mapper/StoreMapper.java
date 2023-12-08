package com.cpr.abyblog.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpr.abyblog.entity.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper extends BaseMapper<Product> {

}