package com.jdbc_learning.more;

import com.jdbc_learning.util.jdbcUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

// 主要要mysql支持批处理的话，必须要加上 ?rewriteBatchedStatements=true
public class moreInsertDeleteTest {

    @Test
    public void test() throws Exception {
        Connection conn = jdbcUtil.getConnection();
        String sql = "insert into pet (name,sex,id) value(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String male = "男";
        String female = "女";
        Random r = new Random();
        for (int i = 227; i <= 20000; i++) {
            ps.setObject(1,"name_" + i);
            if (r.nextInt(9) % 2 == 0) ps.setObject(2,male);
            else ps.setObject(2,female);
            ps.setObject(3,i);
            ps.execute();
        }

        jdbcUtil.closeResoure(conn,ps);
    }

    //方式二，就是在前面加上了一个其他操作而已
    @Test
    public void test1() throws Exception {
        Connection conn = jdbcUtil.getConnection();
        String sql = "insert into pet (name,sex,id) value(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String male = "男";
        String female = "女";
        Random r = new Random();
        for (int i = 20001; i <= 30000; i++) {
            ps.setObject(1,"name_" + i);
            if (r.nextInt(9) % 2 == 0) ps.setObject(2,male);
            else ps.setObject(2,female);
            ps.setObject(3,i);
            ps.addBatch();
            if (i % 500 == 0) {
                ps.executeBatch();
                ps.clearBatch();
            }
        }

        jdbcUtil.closeResoure(conn,ps);
    }

    @Test
    public void testFinal() throws Exception {
        Connection conn = jdbcUtil.getConnection();
        //  这里最终的优化方式是不让其提交，最后进行一次提交即可
        conn.setAutoCommit(false);
        String sql = "insert into pet (name,sex,id) value(?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        String male = "男";
        String female = "女";
        Random r = new Random();
        for (int i = 20001; i <= 30000; i++) {
            ps.setObject(1,"name_" + i);
            if (r.nextInt(9) % 2 == 0) ps.setObject(2,male);
            else ps.setObject(2,female);
            ps.setObject(3,i);
            ps.addBatch();
            if (i % 500 == 0) {
                ps.executeBatch();
                ps.clearBatch();
            }
        }
        // 这里就是相当于将所有的数据进行一个提交
        conn.commit();
        jdbcUtil.closeResoure(conn,ps);
    }
}
