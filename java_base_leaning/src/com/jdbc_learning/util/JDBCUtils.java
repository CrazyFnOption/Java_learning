package com.jdbc_learning.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    // 这个数据库链接池只用造一个就行了，不需要造很多，只需要保证数据库链接池提供一个就可以了
    private static ComboPooledDataSource cpd = new ComboPooledDataSource("hellc3p0");
    public static Connection getConnection() throws SQLException {

        Connection connection = cpd.getConnection();
        return connection;
    }

    private static DataSource dataSource;
    static{
        try {
            Properties pro = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("Druidjdbc.properties");
            // 记住这个地方是一定要写加载的
            pro.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
    public static Connection getConnection2() throws SQLException {
        Connection connection = dataSource.getConnection();
        return connection;
    }

    public static void closeResoure(Connection conn, Statement statement) {
        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResoure(Connection conn, Statement statement, ResultSet rs) {
        try {
            if (statement != null) statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
