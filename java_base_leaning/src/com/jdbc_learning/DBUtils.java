package com.jdbc_learning;


import com.jdbc_learning.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

// 这个类直接封装了对于数据库的增删改查操作，就相当于前面你自己写的相关操作
public class DBUtils {

    @Test
    public void testInsert() throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection connection = JDBCUtils.getConnection2();

        String sql = "insert into student (name,course,id,grade) " +
                "value(?,?,?,?)";
        int id = runner.update(connection, sql, "王舒啸", "算法设计与分析", "201706060230", "98");
        System.out.println("添加了" + id + "记录");
        JDBCUtils.closeResoure(connection,null);

    }


    // 如果要查询特殊值的相关需求的话，一般使用的是ScalarHandler来接受结果集
    @Test
    public void testQuery() throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection connection = JDBCUtils.getConnection2();
        String sql = "select id,name,course,grade from student where id = ?";
        // 数组显示
        ArrayHandler res = new ArrayHandler();
        MapHandler mapHandler = new MapHandler();
        Map<String, Object> query = runner.query(connection, sql, mapHandler, "201706060230");
        System.out.println(query);
        Object[] query1 = runner.query(connection, sql, res, "201706060230");
        for (Object o :query1) {
            System.out.println(o.toString());
        }
        JDBCUtils.closeResoure(connection,null);
    }


    // 下面这种办法就是用来自定义相关的操作机行为
    @Test
    public void testMyown() throws SQLException {
        QueryRunner runner = new QueryRunner();
        Connection connection = JDBCUtils.getConnection2();
        String sql = "select id from student";
        ResultSetHandler<String> handler = new ResultSetHandler<String>() {
            @Override
            public String handle(ResultSet rs) throws SQLException {
                if (rs.next()) {
                    String id = (String) rs.getObject(1);
                    return id;
                }
                return null;
            }
        };
        System.out.printf(runner.query(connection, sql,handler));
        JDBCUtils.closeResoure(connection,null);
    }


}

