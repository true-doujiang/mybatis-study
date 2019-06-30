package com.yhh.mybatis.mapper;


import java.io.InputStream;
import java.util.List;
import com.yhh.mybatis.entity.Orders;
import com.yhh.mybatis.entity.User;
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



    //延迟加载
    /**
     * mybatis默认没有开启延迟加载，需要在SqlMapConfig.xml中setting配置。
     * <setting name="lazyLoadingEnabled" value="true"/>
     * <setting name="aggressiveLazyLoading" value="false"/>
     */
    @Test         // 查询订单关联查询用户，用户信息使用延迟加载
    public void testFindOrdersUserLazyLoading() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();    // 创建代理对象
        OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);

        // 查询订单信息（单表）
        List<Orders> list = mapper.findOrdersUserLazyLoading();
        //System.out.println(list); //你打印出来list就相当于调用里面的user了

        sqlSession.close();//关闭了也可以查到    ，但是没有一级缓存了

        for (Orders orders : list) {
			// 执行getUser()去查询用户信息，这里实现按需加载
			User user = orders.getUser();
			System.out.println(user);
		}

    }

    /**
     * 想使用懒加载必须加入 cglib相关jar
     * SqlMapConfig.xml 开启延迟加载的配置后就要加cglib.jar否则报错
     */
    public static void main(String[] args) throws Exception {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);

        SqlSession sqlSession = factory.openSession();    // 创建代理对象

        OrdersMapperCustom mapper = sqlSession.getMapper(OrdersMapperCustom.class);

        // 查询订单信息（单表）
        List<Orders> list = mapper.findOrdersUserLazyLoading();
        //System.out.println(list); //你打印出来list就相当于调用里面的user了

    }
}