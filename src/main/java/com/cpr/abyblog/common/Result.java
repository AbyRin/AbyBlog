package com.cpr.abyblog.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口统一返回包装类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private String code;
    private String message;
    private Object data;

    // 返回成功信息（无数据）
    public static Result success() {
        return new Result(Constants.CODE_200, "", null);
    }

    // 返回成功信息（有数据）
    public static Result success(Object data) {
        return new Result(Constants.CODE_200, "", data);
    }

    // 返回错误信息
    public static Result error(String code, String message) {
        return new Result(code, message, null);
    }

    // 返回系统错误（500）
    public static Result error() {
        return new Result(Constants.CODE_500, "系统错误", null);
    }
}
