package com.jdbc_learning.Transaction;

import com.jdbc_learning.util.jdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransationTest {

    //*********************考虑数据库事物的时候时的链接******************

    // 要么都做，要么都不做，最重要的就是 connect里面的自动提交，在关闭，完成操作之后都会自动提交
    // 最好的办法，手动来控制自动提交，并且自己再最后的时候在进行关闭，注意某种情况下记得恢复成默认值
    @Test
    public void test1() {
        Connection connection = null;
        try {
            connection = jdbcUtil.getConnection();
            connection.setAutoCommit(false);
            String sql1 = "update pet set name = ? where id = ?";
            update(connection,sql1,"ac自动机","5");
            update(connection,sql1,"机会主义","6");
            System.out.println("更改成功");
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public void update(Connection connection, String sql,Object ... args) {
        PreparedStatement ps = null;
        try {

            ps = connection.prepareStatement(sql);

            // 填充占位符，其里面相应占位符的个数
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeResoure(null, ps);
        }
    }

    //*********************未考虑数据库事物的时候时的链接*****************
    @Test
    public void testone() {
        String sql1 = "update pet set name = ? where id = ?";
        update(sql1,"大傻子","1");
        update(sql1,"二愣子","2");
        System.out.println("更改成功");
    }

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




}
