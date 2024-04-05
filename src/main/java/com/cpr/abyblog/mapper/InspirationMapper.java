package com.cpr.abyblog.mapper;

import com.cpr.abyblog.entity.Inspiration;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Aby
 * @since 2024-04-03 08:42:58
 */
@Mapper
public interface InspirationMapper extends BaseMapper<Inspiration> {

    @Select("SELECT * FROM inspiration")
    List<Inspiration> getInspirationList();
}
