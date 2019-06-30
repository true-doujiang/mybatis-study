package com.yhh.mybatis.mapper;


import java.io.InputStream;

import com.yhh.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

/**
 * 本工程整合了ehcahe分布式缓存框架
 * 步骤readme.txt
 */
public class UserMapperTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**开启二级缓存步骤(以namespace为单位)
     * 1、SqlMapConfig.xml中加入
     * 	 <setting name="cacheEnabled" value="true"/>  虽然是默认的
     * 2、在UserMapper.xml加入<cache type="org.mybatis.caches.ehcache.EhcacheCache" />
     * 3、在classpath下配置ehcache.xml
     * 4、对应的实体类implements Serializable接口
     */
    @Test       // 二级缓存测试
    public void testCache2() throws Exception {

        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();

        UserMapper userMapper1 = sqlSession1.getMapper(UserMapper.class);
        // 第一次发起请求，查询id为1的用户
        User user1 = userMapper1.findUserById(1);
        System.out.println(user1);
        User user11 = userMapper1.findUserById(2);
        System.out.println(user11);

        /**
         * 切记: 这里执行关闭操作，将sqlsession中的数据写到二级缓存区域,否则写不到二级缓存中
         */
        sqlSession1.close();

        //Thread.sleep(5000);

/*
		//使用sqlSession3执行commit()操作
		UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
		User user = userMapper3.findUserById(1);
		user.setUsername("张明明xxx");
		userMapper3.updateUser(user);
		//执行提交，清空UserMapper下边的二级缓存 , 否则出现脏读，同一级缓存的脏读情况一样.
		//sqlSession3.commit();
		sqlSession3.close(); //update时只close不commit也不会进入二级缓存
*/

        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        // 第二次发起请求，查询id为1的用户
        User user2 = userMapper2.findUserById(1);
        System.out.println(user2);
        /**
         * 切记: 这里执行关闭操作，将sqlsession中的数据写到二级缓存区域,否则写不到二级缓存中
         */
        sqlSession2.close();
    }



}