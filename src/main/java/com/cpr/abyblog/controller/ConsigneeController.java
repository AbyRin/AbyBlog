package com.cpr.abyblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cpr.abyblog.entity.Consignee;
import com.cpr.abyblog.mapper.ConsigneeMapper;
import com.cpr.abyblog.service.ConsigneeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Aby
 * @since 2024-02-26 01:29:48
 */
@RestController
@RequestMapping("/consignee")
public class ConsigneeController {

    @Autowired
    private ConsigneeMapper consigneeMapper;

    @Autowired
    private ConsigneeService consigneeService;

    // 按 userId 展示 收件人信息
    @GetMapping("/showConsigneeListByUserId")
    public List<Consignee> showConsigneeList(@RequestParam Integer userId) {
        return consigneeMapper.getConsigneeListByUserId(userId);
    }

    // 更新收件人信息
    @PostMapping("/updateConsignee")
    public ResponseEntity<Integer> updateConsignee(@RequestParam(value = "userId") Integer userId,
                                                   @RequestParam(value = "consigneeId") Integer consigneeId,
                                                   @RequestParam(value = "consigneeName") String consigneeName,
                                                   @RequestParam(value = "consigneeMobile") String consigneeMobile,
                                                   @RequestParam(value = "consigneeProvince") String consigneeProvince,
                                                   @RequestParam(value = "consigneeCity") String consigneeCity,
                                                   @RequestParam(value = "consigneeArea") String consigneeArea,
                                                   @RequestParam(value = "consigneeAddress") String consigneeAddress) {

        // 查询构造器：查询是否已存在该收件人：验证 userId 和 consigneeId
        QueryWrapper<Consignee> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("consignee_id", consigneeId);

        // 唯一标识：一位用户 创建的 一位收件人
        Consignee existingConsignee = consigneeService.getOne(queryWrapper);

        // 查询：购物车中是否存在该收件人？ ->
        if (existingConsignee != null) {  // 存在
            // 查询指定 userId 已创建收件人数量
            long consigneeCreatedQuantity = consigneeService.count(new QueryWrapper<Consignee>().eq("user_id", userId));

            int creatLimit = 3;  // 固定值：收件人 最大创建数

            // 检查购买数量是否在限制范围内？
            if (consigneeCreatedQuantity >= 0 && consigneeCreatedQuantity <= creatLimit) {
                UpdateWrapper<Consignee> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("user_id", userId);
                updateWrapper.eq("consignee_id", consigneeId);
                updateWrapper.eq("consignee_name", consigneeName);
                updateWrapper.eq("consignee_mobile", consigneeMobile);
                updateWrapper.eq("consignee_province", consigneeProvince);
                updateWrapper.eq("consignee_city", consigneeCity);
                updateWrapper.eq("consignee_area", consigneeArea);
                updateWrapper.eq("consignee_address", consigneeAddress);
                consigneeService.update(updateWrapper.set("consignee_name", consigneeName)
                        .set("consignee_mobile", consigneeMobile)
                        .set("consignee_province", consigneeProvince)
                        .set("consignee_city", consigneeCity)
                        .set("consignee_area", consigneeArea)
                        .set("consignee_address", consigneeAddress));
                return new ResponseEntity<>(1001, HttpStatus.OK);  // 状态码：更新收件人信息-成功
            } else {
                // 收件人创建数量 超过限制
                return new ResponseEntity<>(2001, HttpStatus.BAD_REQUEST);  // 状态码：收件人创建数达到上限
            }
        } else {
            // 购物车中不存在该收件人：添加一个 Consignee 类
            Consignee newConsignee = new Consignee();
            newConsignee.setUserId(userId);
            newConsignee.setConsigneeId(consigneeId);
            newConsignee.setConsigneeName(consigneeName);
            newConsignee.setConsigneeMobile(consigneeMobile);
            newConsignee.setConsigneeProvince(consigneeProvince);
            newConsignee.setConsigneeCity(consigneeCity);
            newConsignee.setConsigneeArea(consigneeArea);
            newConsignee.setConsigneeAddress(consigneeAddress);
            consigneeService.save(newConsignee);
            return new ResponseEntity<>(1000, HttpStatus.CREATED);  // 状态码：添加收件人-成功
        }
    }
}
