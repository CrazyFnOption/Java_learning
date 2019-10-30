package com.jdbc_learning.Connection_more;


import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidTest {
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
    @Test
    public static void getConnection() throws SQLException {

        Connection connection = dataSource.getConnection();
        System.out.println(connection);

    }
}
