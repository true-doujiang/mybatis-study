package com.yhh.mybatis.dao;

import com.yhh.mybatis.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 0:00
 * @Version: 1.0
 * @Desc:
 */
public class UserDaoImpl implements UserDao {

    // 需要向dao实现类中注入SqlSessionFactory
    private SqlSessionFactory sqlSessionFactory;

    // 这里通过构造方法注入
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public User findUserById(int id) throws Exception {
        /* SqlSession是线程不安全的，在SqlSesion实现类中除了有接口中的方法（操作数据库的方法）还有数据域属性。
         */
        SqlSession sqlSession = sqlSessionFactory.openSession();
        /*1、将statement的id硬编码了 ("test.findUserById")
         *2、sqlsession方法使用泛型，即使变量类型传入错误，在编译阶段也不报错，不利于程序员开发。
         */
        User user = sqlSession.selectOne("test.findUserById", id);

        sqlSession.close();
        return user;
    }

    @Override
    public List<User> findUserByName(String name) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<User> list = sqlSession.selectList("test.findUserByName", name);

        sqlSession.close();
        return list;
    }


    @Override
    public int insertUser(User user) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int count = sqlSession.insert("test.insertUser", user);

        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public int deleteUser(int id) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int count = sqlSession.delete("test.deleteUser", id);

        sqlSession.commit();
        sqlSession.close();
        return count;
    }

    @Override
    public int updateUser(User user) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int count = sqlSession.update("test.updateUser", user);

        sqlSession.commit();
        sqlSession.close();
        return count;
    }

}
