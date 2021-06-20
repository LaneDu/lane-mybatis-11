package com.lagou.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.mapper.IOrderMapper;
import com.lagou.mapper.IUserMapper;
import com.lagou.mapper.UserMapper;
import com.lagou.pojo.Order;
import com.lagou.pojo.User;
import com.lagou.pojo.UserM;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MybatisTest {



    //需要注释掉@before才可以执行前几个测试方法
    @Test
     public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IOrderMapper mapper = sqlSession.getMapper(IOrderMapper.class);
        List<Order> orders = mapper.findOrderAndUser();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> all = mapper.findAll();
        for (User user : all) {
            System.out.println(user);
        }

    }

    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = build.openSession();
        IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> all = mapper.findAllUserAndRole();
        for (User user : all) {
            System.out.println(user);
        }

    }


    private IUserMapper mapper;
    private IOrderMapper mapper2;
    private UserMapper mapper3;
    private SqlSession sqlSession;
    /**
     * 每次测试前会执行该方法
     * @throws IOException
     */
    @Before
    public void beforTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        sqlSession = build.openSession();
        mapper = sqlSession.getMapper(IUserMapper.class);


    }
    @Test
    public void addUser(){
        User user = new User();
        user.setId(4);
        user.setUsername("测试4");
        mapper.addUser(user);
        sqlSession.commit();
        sqlSession.close();
    }
    @Test
    public void updateUser(){
        User user = new User();
        user.setId(3);
        user.setUsername("测试3");
        mapper.update(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void selectUser(){
        List<User> users = mapper.selectUser();
        sqlSession.commit();
        sqlSession.close();
        for (User user : users) {
            System.out.println(user);
        }

    }
    @Test
    public void deleteUser(){
        mapper.deleteUser(3);
        sqlSession.commit();
        sqlSession.close();

    }
    @Test
    public void test5() throws IOException {
        mapper2 = sqlSession.getMapper(IOrderMapper.class);
        List<Order> orders =  mapper2.findOrderAndUsers();
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void test6() throws IOException {
        mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> alls = mapper.findAlls();
        for (User user : alls
             ) {
            System.out.println(user);

        }

    }

    @Test
    public void pageHelperTest() throws IOException {
        PageHelper.startPage(2,1);
        mapper = sqlSession.getMapper(IUserMapper.class);
        List<User> alls = mapper.findAlls();
        for (User user : alls
        ) {
            System.out.println(user);
            }
        PageInfo<User> pageInfo = new PageInfo<>(alls);
        System.out.println("总条数："+pageInfo.getTotal());
        System.out.println("总页数："+pageInfo.getPages());
        System.out.println("当前页数："+pageInfo.getPageNum());
        System.out.println("每页显示条数："+pageInfo.getPageSize());

    }
    @Test
    public void mapperTest() throws IOException {

        mapper3 = sqlSession.getMapper(UserMapper.class);
        UserM userM = new UserM();
        userM.setId(1);

        List<UserM> users = mapper3.select(userM);
        for (UserM user : users) {
            System.out.println(user);
        }

    }

    @Test
    public void cacheTest() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession1 = build.openSession();
        User user1 = sqlSession1.selectOne("com.lagou.mapper.IUserMapper.findUserById",1);
        System.out.println(user1);
        //因为未提交二级缓存没有存储，所以仍然查询2次
        sqlSession1.commit();

        SqlSession sqlSession2 = build.openSession();
        User user2 = sqlSession2.selectOne("com.lagou.mapper.IUserMapper.findUserById",1);
        System.out.println(user2);
    }

}
