package com.yhh.mybatis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 8:38
 * @Version: 1.0
 * @Desc:
 */

/**
	通过单独的jdbc程序，总结其中的问题  
    1、数据库连接，使用时就创建，不使用立即释放，对数据库进行频繁连接开启和关闭，造成数据库资源浪费，影响 数据库性能。
	设想：使用数据库连接池管理数据库连接。
	2、将sql语句硬编码到java代码中，如果sql 语句修改，需要重新编译java代码，不利于系统维护。
	设想：将sql语句配置在xml配置文件中，即使sql变化，不需要对java代码进行重新编译。
	3、向preparedStatement中设置参数，对占位符号位置和设置参数值，硬编码在java代码中，不利于系统维护。
	设想：将sql语句及占位符号和参数全部配置在xml中。
	4、从resutSet中遍历结果集数据时，存在硬编码，将获取表的字段进行硬编码，，不利于系统维护。
	设想：将查询的结果集，自动映射成java对象。
 */
public class JDBCTest {


	public static void main(String[] args) {
		//数据库连接
		Connection connection = null;
		//预编译的Statement，使用预编译的Statement提高数据库性能    
		PreparedStatement pstat = null;  // Statement存在SQL注入的风险
		//结果 集
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//通过驱动管理类获取数据库链接
			//connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "ok");
			connection =  DriverManager.getConnection("jdbc:mysql://127.0.0.1/mybatis?user=root&password=ok");
			//定义sql语句 ?表示占位符
			String sql = "select * from user where username = ?";
			//先   获取预处理statement
			pstat = connection.prepareStatement(sql);
			//再   设置参数，第一个参数为sql语句中参数的序号（从1开始），第二个参数为设置的参数值
			pstat.setString(1, "王五");

			resultSet = pstat.executeQuery();

			//遍历查询结果集
			while(resultSet.next()){
				System.out.println(resultSet.getString("id")+"  "+resultSet.getString("username"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//释放资源
			if(resultSet!=null){
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstat!=null){
				try {
					pstat.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection!=null){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
