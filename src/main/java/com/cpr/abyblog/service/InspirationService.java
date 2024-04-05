package com.cpr.abyblog.service;

import com.cpr.abyblog.entity.Inspiration;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Aby
 * @since 2024-04-03 08:42:58
 */
public interface InspirationService extends IService<Inspiration> {

    List<Inspiration> getInspirationList();
}
