package com.lagou.mapper;

import com.lagou.pojo.Order;
import com.lagou.pojo.Role;
import com.lagou.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserMapper {


    public List<User> findAll();

    public List<User> findAllUserAndRole();


    @Insert("insert into user values(#{id},#{username})")
    public void addUser(User user);

    @Update("update user set username=#{username} where id =#{id}")
    public void update (User user);

    @Select("select * from user")
    public List<User> selectUser ();

    @Delete("delete from user where id =#{id}")
    public void deleteUser(Integer id);

    @Select("select * from user where id = #{id}")
    public User selectUserById(Integer id);

    @Select("select * from user")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "orderList",column = "id",javaType = List.class,
            many=@Many(select = "com.lagou.mapper.IOrderMapper.findOrderByUid"))
    })
    public List<User> findAlls();


    public User findUserById(Integer id);

}




