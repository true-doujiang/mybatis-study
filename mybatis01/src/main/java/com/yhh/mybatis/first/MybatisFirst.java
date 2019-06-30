package com.yhh.mybatis.first;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.yhh.mybatis.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 8:38
 * @Version: 1.0
 * @Desc:
 */

/**
3.10	总结

	1、#{}和${}	
	#{}表示一个占位符号，#{}接收输入参数，类型可以是 简单类型、pojo、hashmap。
	   如果接收简单类型，#{}中可以写成value或其它名称。
	#{}接收pojo对象值，通过OGNL读取对象中的属性值，通过 属性.属性.属性...的方式获取对象属性值。
	
	${}表示一个拼接符号，会引用sql注入，所以不建议使用${}。
	${}接收输入参数，类型可以是 简单类型、pojo、hashmap。
	   如果接收简单类型，${}中只能写成value。
	${}接收pojo对象值，通过OGNL读取对象中的属性值，通过 属性.属性.属性...的方式获取对象属性值。(同上)
	
	2、selectOne和selectList
	selectOne表示查询出一条记录进行映射。如果使用selectOne可以实现使用selectList也可以实现（list中只有一个对象）。
	selectList表示查询出一个列表（多条记录）进行映射。如果使用selectList查询多条记录，不能使用selectOne。
	如果使用selectOne报错：
	org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 4


只需要
mybatis-3.2.7.jar  核心包
mysql-connector-java-5.1.7-bin.jar  就可以工作了

lib目录下为其它依赖包
 */
public class MybatisFirst {


	public static SqlSession sqlSession;


	//调试用的
	public static void main(String[] args) throws IOException {
		String resource = "SqlMapConfig.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		
		//org.apache.ibatis.session.defaults.DefaultSqlSessionFactory
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(inputStream);
		
		//org.apache.ibatis.session.defaults.DefaultSqlSession
		sqlSession = factory.openSession();
		
		User user = sqlSession.selectOne("test.findUserById",1);
		System.out.println(user);
		
		sqlSession.close();  // 释放资源
	}

	@BeforeClass
	public static void beforeClass() throws IOException{
		String resource = "SqlMapConfig.xml";
		//Resources从根目录找起      mybatis框架提供的     inputStream框架都帮你关上了
		InputStream inputStream = Resources.getResourceAsStream(resource);

		// 创建会话工厂，传入mybatis的配置文件信息   org.apache.ibatis.session.defaults.DefaultSqlSessionFactory
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(inputStream);

		// 通过工厂得到SqlSession   每次openSession返回的都是一个新的sqlSession
		sqlSession = factory.openSession();
		
	}
	
	// 根据id查询用户信息，得到一条记录结果
	@Test
	public void findUserByIdTest() throws IOException {
		/*
		通过SqlSession操作数据库
		第一个参数：映射文件中statement的id，等于   namespace+"."+statement的id
		第二个参数：指定和映射文件中所匹配的parameterType类型的参数
		sqlSession.selectOne结果 是与映射文件中所匹配的resultType类型的对象
		*/
		//org.apache.ibatis.session.defaults.DefaultSqlSession
		User user = sqlSession.selectOne("test.findUserById",1);

		System.out.println(user);
		sqlSession.close();  // 释放资源
	}
	

	// 根据用户名称模糊查询用户列表      ${}看日志打印的SQL语句直接把参数拼接上去的
	@Test
	public void findUserByNameTest() throws IOException {
		List<User> list1 = sqlSession.selectList("test.findUserByName", "小明");
		//List<User> list1 = sqlSession.selectList("test.findUserByName", "' or 1=1 -- '"); //SQL注入了
		System.out.println(list1);
		sqlSession.close();
		
		//如果使用selectOne报错：
		//org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 4
	}

	// 添加用户信息
	@Test
	public void insertUserTest() throws IOException {
		//Transaction
		// 插入用户对象
		User user = new User(1,"王小军e","1",new Date(),"河南郑州");
		sqlSession.insert("test.insertUser", user);
		// 提交事务，否则插不进去(Rolling back JDBC Connection)，(和spring合作后就不需要手动提交了) 
		sqlSession.commit();  
		
		// 获取用户信息主键(要在user.xml里配<selectKey....后才可以查到)
		int fanhuiID = user.getId();
		System.out.println(fanhuiID);   
		sqlSession.close();
	}
	
	// 根据id删除 用户信息
	@Test
	public void deleteUserTest() throws IOException {
		// 传入id删除 用户
		int deleteCount = sqlSession.delete("test.deleteUser", 50);
		System.out.println(deleteCount); 
		// 提交事务,否则删不掉
		sqlSession.commit();
		sqlSession.close();
	}

	
	@Test	    // 更新用户信息
	public void updateUserTest() throws IOException {
		// 更新用户信息  必须设置id
		User user = new User(41,"王小军2","2",new Date(),"新疆大山脉");
		int updateCount = sqlSession.update("test.updateUser", user);
		System.out.println(updateCount);
		sqlSession.commit();  // 提交事务,否则更新不了
		sqlSession.close();  
	}
}
