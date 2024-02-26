package com.cpr.abyblog.mapper;

import com.cpr.abyblog.entity.Consignee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Aby
 * @since 2024-02-26 01:29:48
 */
@Mapper
public interface ConsigneeMapper extends BaseMapper<Consignee> {

    // 根据 userId 查询对应所有 consignee
    @Select("SELECT * FROM consignee WHERE user_id = #{userId}")
    List<Consignee> getConsigneeListByUserId(@Param("userId") Integer userId);

}
