package com.yhh.mybatis.mapper;


import java.io.InputStream;
import java.util.List;

import com.yhh.mybatis.entity.Orders;
import com.yhh.mybatis.entity.User;
import com.yhh.mybatis.vo.OrdersCustom;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class OrdersMapperCustomTest {


    private SqlSessionFactory sqlSessionFactory;


    @Before
    public void setUp() throws Exception {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 关联查询 resultType、resultMap 对比
     */
    @Test    //查询订单关联查询用户使用resultType
    public void testFindOrdersUser() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);

        List<OrdersCustom> list = mapper.findOrdersUser();
        System.out.println(list);
        sqlSession.close();
    }

    @Test  //查询订单关联查询用户使用resultMap
    public void testFindOrdersUserResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);

        List<Orders> list = mapper.findOrdersUserResultMap();
        System.out.println(list);
        sqlSession.close();
    }


    /**
     * 一对多查询
     */
    @Test               //查询订单(关联用户)及订单明细
    public void testFindOrdersAndOrderDetailResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
        List<Orders> list = mapper.findOrdersAndOrderDetailResultMap();
        System.out.println(list);
        sqlSession.close();
    }


    /**
     * 多对多查询
     */
    @Test  			//查询用户购买商品信息
    public void testFindUserAndItemsResultMap() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);
        List<User> list = mapper.findUserAndItemsResultMap();
        System.out.println(list);
        sqlSession.close();
    }

}