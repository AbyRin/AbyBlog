package com.cpr.abyblog.service.impl;

import com.cpr.abyblog.entity.Inspiration;
import com.cpr.abyblog.mapper.InspirationMapper;
import com.cpr.abyblog.service.InspirationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Aby
 * @since 2024-04-03 08:42:58
 */
@Service
public class InspirationServiceImpl extends ServiceImpl<InspirationMapper, Inspiration> implements InspirationService {

    @Override
    public List<Inspiration> getInspirationList() {
        return baseMapper.getInspirationList();
    }
}
