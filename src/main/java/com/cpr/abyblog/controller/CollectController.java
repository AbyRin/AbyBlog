package com.cpr.abyblog.controller;

import com.cpr.abyblog.entity.Collect;
import com.cpr.abyblog.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CollectController {

    @Autowired
    private CollectMapper collectMapper;

    // 展示：商品收藏夹
    @GetMapping("/collect/productCollection")
    public List<Collect> showProductCollection() {
        return collectMapper.selectList(null);
    }

    // 展示：图库收藏夹
    @GetMapping("/collect/inspirationCollection")
    public List<Collect> showInspirationCollection() {
        return collectMapper.selectList(null);
    }

    // 展示：话题收藏夹
    @GetMapping("/collect/commentCollection")
    public List<Collect> showCommentCollection() {
        return collectMapper.selectList(null);
    }
}
