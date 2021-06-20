package com.lagou.test;

import com.lagou.dao.IUserDao;
import com.lagou.dao.UserDaoImpl;
import com.lagou.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {


    @Test
    public void test1() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> users = sqlSession.selectList("com.lagou.dao.IUserDao.findAll");
        for (User user : users) {
            System.out.println(user);
        }
        sqlSession.close();
    }

        @Test
        public void test2() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user1 = new User();
        user1.setId(4);
        user1.setUsername("xx");
        sqlSession.insert("com.lagou.dao.IUserDao.saveUser",user1);
        sqlSession.commit();
        sqlSession.close();
    }

        @Test
        public void test5() throws IOException {

//            IUserDao userDao =  new UserDaoImpl();
//            List<User> users = userDao.findAll();

            InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            IUserDao mapper = sqlSession.getMapper(IUserDao.class);

            List<User> users = mapper.findAll();
            for (User user : users) {
                System.out.println(user);
            }
        }

    @Test
    public void test6() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        User user1 = new User();
        user1.setId(1);

        List<User> users = mapper.findByCondition(user1);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test7() throws IOException {

        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        IUserDao mapper = sqlSession.getMapper(IUserDao.class);
        int ids[] ={1,2};
        List<User> users = mapper.findByIds(ids );
        for (User user : users) {
            System.out.println(user);
        }
    }
}