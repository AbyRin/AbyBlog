package com.cpr.abyblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpr.abyblog.entity.Collect;
import com.cpr.abyblog.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collect")
public class CollectController {

    @Autowired
    private CollectService collectService;

    // 分页查询：按类别 展示收藏夹
    @GetMapping("/pageCollection")
    public List<Collect> showProductCollection(@RequestParam Integer userId,
                                               @RequestParam String type) {
        IPage<Collect> collectDTO = new Page<>();

        collectDTO.setCurrent(userId);
        collectDTO.setSize(10); // 单页展示数据条数

        QueryWrapper<Collect> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("type", type);

        return collectService.page(collectDTO, queryWrapper).getRecords();
    }
}
