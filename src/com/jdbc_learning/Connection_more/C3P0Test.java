package com.jdbc_learning.Connection_more;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class C3P0Test {

    // 这种方式还是把配置文件直接写到了代码中，应该是把信息写到配置文件之中。
    @Test
    public void test() throws PropertyVetoException, SQLException {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/test" );
        cpds.setUser("root");
        cpds.setPassword("w19971128");

        // 设置相关数据库链接相关配置 参数
        // 设置初始数据库链接词链接数
        cpds.setInitialPoolSize(10);
        Connection connection = cpds.getConnection();
        System.out.println(connection);

        // 销毁链接池 销毁c3p0的链接池
        // DataSources.destroy(cpds);
    }

    // 方式二 使用配置文件来写代码
    @Test
    public void test2() throws SQLException {
        ComboPooledDataSource cpd = new ComboPooledDataSource("hellc3p0");
        Connection connection = cpd.getConnection();
        System.out.println(connection);
    }


}
