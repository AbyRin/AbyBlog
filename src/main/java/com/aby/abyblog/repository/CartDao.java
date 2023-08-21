package com.aby.abyblog.repository;

import com.aby.abyblog.entity.Cart;
import com.aby.abyblog.entity.MyCart;
import org.apache.ibatis.annotations.*;

import java.util.List;

// week 12
@Mapper
public interface CartDao {
    @Update("update cart set num=#{num} where cart_id=#{cartId}")
    public int updateCart(Integer cartId,Integer num);

    @Select("select * from vcart where email=#{email}")
    public List<MyCart> findByEmail(String email);

    @Select("select * from cart where email=#{email} and productid=#{productid} limit 1")
    public Cart findCart(String email, String productid);

    @Insert("insert into cart(email,productid,num) values(#{email},#{productid},#{num})")
    public void addCart(Cart cart);

    // 更正 save 方法
    void save(Cart cart);

    // week 13
    @Delete("delete from cart where cart_id = #{cartId}")
    void deleteCart(Integer cartId);

    @Delete("delete from cart where email=#{email}")
    void clearCart(String email);

    // week 15
    @Select("select * from cart where cart_id=#{cart_id}")
    Cart findCarById(int cart_id);
    @Select("select * from vcart where cart_id=#{cartID}")
    MyCart findMyCarById(Integer cartID);
}
