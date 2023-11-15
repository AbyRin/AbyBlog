package com.cpr.abyblog.repository;

import com.cpr.abyblog.entity.Cart;
import com.cpr.abyblog.entity.UserCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

// week 12
@Mapper
public interface CartDao {
    @Update("update cart set num=#{num} where cart_id=#{cart_id}")
    public int updateCart(Integer cart_id,Integer num);

    @Select("select * from vcart where email=#{email}")
    public List<UserCart> findByEmail(String email);

    @Select("select * from cart where email=#{email} and cart_id=#{cart_id} limit 1")
    public Cart findCart(String email, String cart_id);

    @Insert("insert into cart(email,cart_id,num) values(#{email},#{cart_id},#{num})")
    public void addCart(Cart cart);

    // 更正 save 方法
    void save(Cart cart);

    // week 13
    @Delete("delete from cart where cart_id = #{cart_id}")
    void deleteCart(Integer cart_id);

    @Delete("delete from cart where email=#{email}")
    void clearCart(String email);

    // week 15
    @Select("select * from cart where cart_id=#{cart_id}")
    Cart findCarById(int cart_id);
    @Select("select * from vcart where cart_id=#{cart_iD}")
    UserCart findMyCarById(Integer cart_iD);
}

