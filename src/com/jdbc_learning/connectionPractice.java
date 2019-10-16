package com.jdbc_learning;


import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class connectionPractice {

    @Test
    public void testConnect() throws SQLException {
        Driver driver = new com.mysql.cj.jdbc.Driver();

        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "w19971128");

        System.out.println(connection);
    }

}
