package com.jdbc_learning;

import com.jdbc_learning.util.jdbcUtil;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

// 一般调用jdbc相关步骤
// 1.获取数据库的链接
// 2.写sql语句，并且用preparedStatement填充
// 3.填充占位符
// 4.执行
// 5.关闭相关的操作

public class prepareStatementConnection {

    @Test
    public void updateCommonTest() {
        String sql = "insert into pet value (? , ? , ?)";
        update(sql, "郭宇轩","女","220");
    }

    // 通用的增删改操作
    public void update(String sql,Object ... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = jdbcUtil.getConnection();
            ps = connection.prepareStatement(sql);

            // 填充占位符，其里面相应占位符的个数
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeResoure(connection, ps);
        }
    }

    @Test
    public void testUpdate() {
        // 1.获取数据库的链接
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = jdbcUtil.getConnection();
            String sql = "update pet set name = ? where id = ?";
            ps = connection.prepareStatement(sql);

            ps.setObject(1, "gay轩");
            ps.setObject(2, "220");

            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeResoure(connection, ps);
        }

    }

    @Test
    public void testInsert()  {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties");
            Properties pros = new Properties();
            pros.load(is);

            String user = pros.getProperty("user");
            String password = pros.getProperty("password");
            String url = pros.getProperty("url");
            String driverClass = pros.getProperty("driverClass");

            // 2.加载驱动
            Class.forName(driverClass);

            // 3.获得链接
            conn = DriverManager.getConnection(url,user,password);
            //System.out.println(conn);

            // 4.预编译sql语句
            String sql = "insert into pet values (?,?,?)";
            ps = conn.prepareStatement(sql);

            // 5.填充占位符
            ps.setString(1,"guoyuxuan");
            ps.setString(2, "fm");
            ps.setString(3, "0220");

            ps.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
}
