package com.cpr.cprblog.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity(name="member")
public class Member {
    @Id
    private String email;
    private String password;
    private String name;
    private String address;
    private int point;

    // 生成setter和getter方法
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
}
