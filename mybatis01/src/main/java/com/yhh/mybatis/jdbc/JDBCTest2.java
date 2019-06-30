package com.yhh.mybatis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 8:38
 * @Version: 1.0
 * @Desc:
 */

/**

从此结果可以清楚地看出，创建一个Connection对象，用了250 毫秒；而执行SQL的时间用了170毫秒。
创建一个Connection对象用了250毫秒！这个时间对计算机来说可以说是一个非常奢侈的！
这仅仅是一个Connection对象就有这么大的代价，设想一下另外一种情况：如果我们在Web应用程序中，
为用户的每一个请求就操作一次数据库，当有10000个在线用户并发操作的话，对计算机而言，
仅仅创建Connection对象不包括做业务的时间就要损耗10000×250ms= 250 0000 ms = 2500 s = 41.6667 min,竟然要41分钟！！！
如果对高用户群体使用这样的系统，简直就是开玩笑！
 */
public class JDBCTest2 {



	public static void main(String[] args) {
		
		long beforeTimeOffset = -1L; //创建Connection对象前时间  
	    long afterTimeOffset = -1L; //创建Connection对象后时间  
	    long executeTimeOffset = -1L; //创建Connection对象后时间  
	    
		Connection connection = null;
		PreparedStatement pstat = null;  // Statement存在SQL注入的风险
		ResultSet resultSet = null;
		String sql = "select * from user where username = ?";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");

			beforeTimeOffset = new Date().getTime();  
		    System.out.println("before:\t\t" + beforeTimeOffset);  

		    // 获取连接
		    //connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatis?characterEncoding=utf-8", "root", "ok");
			connection =  DriverManager.getConnection("jdbc:mysql://127.0.0.1/mybatis?user=root&password=ok");
			
			afterTimeOffset = new Date().getTime();  
		    System.out.println("after:\t\t" + afterTimeOffset);  
		    System.out.println("Create Costs:\t\t" + (afterTimeOffset - beforeTimeOffset) + " ms");  
			
			pstat = connection.prepareStatement(sql);
			pstat.setString(1, "王五");
			resultSet = pstat.executeQuery();
			
			executeTimeOffset = new Date().getTime();  
		    System.out.println("Exec Costs:\t\t" + (executeTimeOffset - afterTimeOffset) + " ms");  
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
				pstat.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
