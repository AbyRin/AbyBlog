package com.cpr.abyblog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("collect")
public class Collect {
    @TableId(type = IdType.AUTO)
    private Integer userId;

    private Integer productId;

    private Integer insImgId;

    private Integer commentId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getInsImgId() {
        return insImgId;
    }

    public void setInsImgId(Integer insImgId) {
        this.insImgId = insImgId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "Collect{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", insImgId=" + insImgId +
                ", commentId=" + commentId +
                '}';
    }
}
