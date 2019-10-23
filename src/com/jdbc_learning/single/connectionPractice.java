package com.jdbc_learning.single;


import com.mysql.cj.jdbc.Driver;
import org.junit.Test;


import java.io.InputStream;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class connectionPractice {


    // 记住sql里面进行添加 从外界接受的变量的话 ，可以直接用占位符来进行填充，可以有效的避免拼串。

    // prepareStatementConnection的好处
    // 1.可以操作Blob的数据，图片以及视频之类的
    // 2.可以高效的批量执行sql语句
    @Test
    public void testConnect1() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();

        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "w19971128");

        System.out.println(connection);
    }

    @Test
    //final版本，可以实现相应的配置文件和代码的分离，后期可以很好的更改
    public void testConnect2() throws Exception {
        // 1.读取基本信息
        InputStream is = connectionPractice.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties pros = new Properties();
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        // 2.加载驱动
        Class.forName(driverClass);

        // 3.获得链接
        Connection conn = DriverManager.getConnection(url,user,password);
        System.out.println(conn);
    }

}
