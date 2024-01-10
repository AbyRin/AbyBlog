package com.cpr.abyblog.dto;

import lombok.Data;

// 数据传输对象-用户身份信息
@Data
public class UserDTO {
    private String email;
    private String password;
    private String nickName;
    private String avatar;
}
