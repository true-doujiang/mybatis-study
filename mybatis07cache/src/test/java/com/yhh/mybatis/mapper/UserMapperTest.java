package com.yhh.mybatis.mapper;


import java.io.InputStream;

import com.yhh.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class UserMapperTest {


    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void setUp() throws Exception {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }



    @Test       // 一级缓存测试
    public void testCache1() throws Exception{
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        /**
         * 下边查询使用一个SqlSession
         */
        //第一次发起请求，查询id为1的用户
        User user1 = userMapper.findUserById(1);
        System.out.println("user1=" + user1);

        /**
         * 如果sqlSession去执行commit操作（执行插入、更新、删除），
         * 清空SqlSession中的一级缓存，避免脏读。
         */
        //更新user1的信息
		//user1.setUsername("测试用户33");
		//userMapper.updateUser(user1);
		//执行commit操作去清空缓存 ,切记：如果不提交缓存中放的是"测试用户33"对象，而我又没有插入到DB，
		//而下面用的是我的缓存，就产生了脏读。
		//就算我不提交事务，下面的查询任然会发出sql语句，但这也避免不了脏读.
        //总结：执行插入、更新、删除后一定要commit,否则数据会回滚, 而且再继续使用这个sqlSession将会出现脏读
		//sqlSession.commit();

        //第二次发起请求，查询id为1的用户
        User user2 = userMapper.findUserById(1);
        System.out.println("user2=" + user2);

        sqlSession.close();
    }



    /**
     * 开启二级缓存步骤(以namespace为单位)
     * 1、SqlMapConfig.xml中加入 <setting name="cacheEnabled" value="true"/>  虽然是默认的
     * 2、在UserMapper.xml加入<cache />
     * 3、对应的实体类implements Serializable接口
     *
     * 开启二级缓存后 log4j会打印   [DEBUG] Cache Hit Ratio [com.yhh.mybatis.mapper.UserMapper]: 0.5
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
        /**
         * 切记: 这里执行关闭操作，将sqlsession中的数据写到二级缓存区域,否则写不到二级缓存中
         */
        sqlSession1.close();

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