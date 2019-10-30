package com.jdbc_learning.util;

import org.junit.Test;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class jdbcUtil {

    // 开启数据库的链接
    public static Connection getConnection() throws Exception {
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

        String url = pros.getProperty("url");
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String driverClass = pros.getProperty("driverClass");

        Class.forName(driverClass);
        return DriverManager.getConnection(url, user, password);
    }

    // 关闭数据库链接与sql语句操作

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
