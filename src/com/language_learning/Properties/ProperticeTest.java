package com.language_learning.Properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ProperticeTest {
    public static void main(String[] args) throws IOException {
        Properties pros = new Properties();
        FileInputStream fis = new FileInputStream("src/jdbc.properties");
        pros.load(fis);

        String url = pros.getProperty("url");
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String driverClass = pros.getProperty("driverClass");

        System.out.println(url);
        System.out.println(user);
        System.out.println(password);
        System.out.println(driverClass);
    }
}
