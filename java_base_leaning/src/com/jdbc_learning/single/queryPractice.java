package com.jdbc_learning.single;

import com.jdbc_learning.util.jdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

// 针对于相应的表的单独操作
public class queryPractice {
    /*
    查询的步骤， 使用sql语句，并且考虑要不要设置叠名，然后将信息存储到Resultset，然后通过ResultSetMetaData来获取相关信息
    最后完成对数据的操作

    ResultSetMetaData 与 ResultSet的区别
    getColumnLabel 与 getColumnName的区别

    反射在类里面的妙用。
        Field field = pet.class.getDeclareedField(columnName);
        field.setAccessible(true);
        field.set(pet, colvalue);
     */

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

            // 这个点需要注意的是 数据库中存储的值 一般都存储在 ResultSet
            // 而 关于数据库相应的属性，比如行名，列名之类的，都存储在ResultSetMetaData
            while(resultSet.next()) {
                // 处理每一个数据的每一列
                // Pet pet = new Pet();
                for (int i = 0; i < columnCount; i++) {
                    // 获得相应的列值
                    Object colvalue = resultSet.getObject(i + 1);
                    // 获得相应列的列名字
                    String columnName = rsmd.getColumnLabel(i + 1);
                    /* 反射！！！
                    下面这个知识就是相应的反射知识。
                    当数据库列名与类的数据变量名相同的时候就用下面这个操作：
                    Field field = pet.class.getDeclareedField(columnName);
                    field.setAccessible(true);
                    field.set(pet, colvalue);


                    而当其不相同的时候，可以直接在写sql语句的时候，给相应要查询的列名，直接写一个重叠的名字就可以了，并且不能用上面
                    getColumnName (),因为这个是获得列名字，我们得需要获得的是列的别名。

                    直接使用 getColumnLabel:就算没有别名，也可以直接使用原本的名字。
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
