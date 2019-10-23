package com.jdbc_learning.single;


import com.jdbc_learning.util.jdbcUtil;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

// 这个类具体的作用就是去寻找不同类的返回类型
// 根据类类首先相应的泛型 实现下面获取不同类的不同对象
// 这里最重要的就是类的泛型

public class queryCommonPractice {

    // 返回的是一条对象。
    public <T> T getInstance(Class<T> clazz,String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
             connection = jdbcUtil.getConnection();
             ps = connection.prepareStatement(sql);
             for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
             }
             rs = ps.executeQuery();
             ResultSetMetaData metaData = rs.getMetaData();
             int columnCount = metaData.getColumnCount();
             if (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object colValue = rs.getObject(i + 1);
                    String colName = metaData.getColumnLabel(i + 1);

                    // 接下来就是开始反射映射到相应的值上面去
                    // getField 只能获取父类继承下来public 而 下面使用的这个可以获取全部
                    Field field = clazz.getDeclaredField(colName);
                    field.setAccessible(true);
                    field.set(t,colValue);
                }
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeResoure(connection, ps, rs);
        }
        return null;
    }

    // 返回多 条对象
    // 由于这里没有去写一个相应的类，所以就不在这个地方进行测试了。
    public <T> List<T> getForList(Class<T>clazz, String sql,Object...args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = jdbcUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            ArrayList<T> list = new ArrayList<>();
            while (rs.next()) {
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object colValue = rs.getObject(i + 1);
                    String colName = metaData.getColumnLabel(i + 1);

                    // 接下来就是开始反射映射到相应的值上面去
                    // getField 只能获取父类继承下来public 而 下面使用的这个可以获取全部
                    Field field = clazz.getDeclaredField(colName);
                    field.setAccessible(true);
                    field.set(t,colValue);
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.closeResoure(connection, ps, rs);
        }
        return null;
    }



}


