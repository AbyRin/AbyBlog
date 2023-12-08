package com.cpr.abyblog.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpr.abyblog.entity.Collect;
import com.cpr.abyblog.entity.User;
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

    // 展示：商品收藏夹
    @GetMapping("/showProductCollection")
    public List<Collect> showProductCollection(@RequestParam Integer userId,
                                               @RequestParam Integer productId) {
        IPage<Collect> productDTO = new Page<>(userId, productId);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("product_id", productId);
        return collectService.list(productDTO);
    }

    // 展示：图库收藏夹
    @GetMapping("/showInspirationCollection")
    public List<Collect> showInspirationCollection(@RequestParam Integer userId,
                                                   @RequestParam Integer inspirationId) {
        IPage<Collect> inspirationDTO = new Page<>(userId, inspirationId);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("inspiration_id", inspirationId);
        return collectService.list(inspirationDTO);
    }

    // 展示：话题收藏夹
    @GetMapping("/showForumCollection")
    public List<Collect> showCommentCollection(@RequestParam Integer userId,
                                               @RequestParam Integer forumId) {
        IPage<Collect> forumDTO = new Page<>(userId, forumId);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("forum_id", forumId);
        return collectService.list(forumDTO);
    }
}
