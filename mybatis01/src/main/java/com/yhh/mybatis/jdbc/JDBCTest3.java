package com.yhh.mybatis.jdbc;

import java.sql.*;

import org.junit.Test;

/**
 * @Author: -小野猪-
 * @Date: 2019/6/30 8:38
 * @Version: 1.0
 * @Desc:
 */
public class JDBCTest3 {


    /**
     * 实现批处理第一种方式
     * 1.可以发多条sql
     * 2.sql没有预处理
     *
     * <p>
     * 优点：可以向数据库发送多条不同的ＳＱＬ语句。
     * 缺点：
     * SQL语句没有预编译。
     * 当向数据库发送多条语句相同，但仅参数不同的SQL语句时，需重复写上很多条SQL语句
     */
    @Test
    public void test1() throws SQLException {
        Connection conn = null;
        Statement st = null; //不是PreparedStatement
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();
            String sql1 = "insert into testbatch(id,username) values('1','aaa')";
            String sql2 = "update testbatch set username='bbb' where id='1'";

            st = conn.createStatement();  //st里维护了一个list

            st.addBatch(sql1);
            st.addBatch(sql2);

            //[3,4]  每条sql语句影响的行数
            int a[] = st.executeBatch();

            st.clearBatch();//清除批处理命令  别忘了
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }


    /**
     * 实现批处理的第二种方式
     * 1.只有一条sql，适合做批量插入、批量更新
     * 2.sql语句预处理了(对应同一个sql语句要执行多次的话，最好将它预编译，以调高DB性能)
     *
     * <p>
     * 优点：发送的是预编译后的SQL语句，执行效率高。
     * 缺点：只能应用在SQL语句相同，但参数不同的批处理中。
     * 因此此种形式的批处理经常用于在同一个表中批量插入数据，或批量更新表的数据。
     *
     * <p>
     * MySQL 1s大概能插入1000条，一千万条要花3个小时
     * Oracle 3分钟就可以插完。
     * <p>
     * 我的MySQL 插入1000条要70s左右  弱爆了
     */
	/*
	 sql = "INSERT INTO testbatch (name,password) VALUES
		('张三','123'),
		('李四','123'),
		('王五','123'),
		('赵六','123'),
		('田七','123')........";
		这样效率更高点
	 */
    @Test
    public void test2() throws SQLException {
        long starttime = System.currentTimeMillis();
        Connection conn = null;
        PreparedStatement st = null; //是PreparedStatement
        ResultSet rs = null;
        try {
            conn = JdbcUtils.getConnection();

            boolean autoCommit = conn.getAutoCommit();
            System.out.println("autoCommit=" + autoCommit); // true
            System.out.println(conn.getCatalog());  //数据库名字
            System.out.println(conn.getSchema());
            DatabaseMetaData metaData = conn.getMetaData();
            System.out.println(metaData);

            String sql = "insert into testbatch(id,username) values(?, ?)"; //作批量插入   批量更新
            st = conn.prepareStatement(sql);

            for (int i = 1; i <= 1000; i++) {
                st.setInt(1, i);
                st.setString(2, "aa" + i);

                st.addBatch();    //添加到st维护的list里

                if (i % 10 == 0) {
                    // 有一条记录插入失败异常 其它记录照样可以插入
                    st.executeBatch();
                    st.clearBatch();  //清除批处理命令  别忘了   TODO 不清理也没有多插入啊
                    System.out.println("i=" + i);
                }
            }

            st.executeBatch();
            st.clearBatch();
        } finally {
            JdbcUtils.release(conn, st, rs);
        }

        long endtime = System.currentTimeMillis();
        System.out.println("PreparedStatement总花了：" + (endtime - starttime) / 1000 + "秒");
    }

}
