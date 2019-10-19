package com.jdbc_learning;

import com.jdbc_learning.util.jdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class queryPractice {

    public void query(String sql, Object... args) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = jdbcUtil.getConnection();
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1,args[i]);
            }
            resultSet = ps.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();

            while(resultSet.next()) {
                // 处理每一个数据的每一列
                // Pet pet = new Pet();
                for (int i = 0; i < columnCount; i++) {
                    // 获得相应的列值
                    Object colvalue = resultSet.getObject(i + 1);
                    // 获得相应列的列名字
                    String columnName = rsmd.getColumnName(i + 1);
                    /*
                    下面这个知识就是相应的反射知识。
                    Field field = pet.class.getDeclareedField(columnName);
                    field.setAccessible(true);
                    field.set(pet, colvalue);
                     */
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeResoure(conn, ps, resultSet);
        }
    }

    @Test
    public void test1() throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            conn = jdbcUtil.getConnection();
            String sql = "select * from pet where id = ? or id = ?";
            ps = conn.prepareStatement(sql);
            ps.setObject(1, "220");
            ps.setObject(2, "222");
            resultSet = ps.executeQuery();

            // 注意与其他迭代器的区别
            while(resultSet.next()) {
                String name = resultSet.getString(1);
                String sex = resultSet.getString(2);
                int id = resultSet.getInt(3);
                System.out.println(name + " " + sex + " " + id + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeResoure(conn, ps, resultSet);
        }
    }
}
