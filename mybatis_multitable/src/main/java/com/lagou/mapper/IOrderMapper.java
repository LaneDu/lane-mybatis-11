package com.lagou.mapper;

import com.lagou.pojo.Order;
import com.lagou.pojo.User;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IOrderMapper {


    public List<Order> findOrderAndUser();

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "orderTime",column = "ordertime"),
            @Result(property = "total",column = "total"),
            @Result(property = "user",column = "uid",javaType = User.class,
            one = @One(select = "com.lagou.mapper.IUserMapper.selectUserById")),
    })
    @Select("select * from orders ")
    public List<Order> findOrderAndUsers();

    @Select("select * from orders where uid = #{uid}")
    public List<Order> findOrderByUid(Integer uid);




}
