package com.jdbc_learning.single;

import com.jdbc_learning.util.jdbcUtil;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// mediun blob 小于 16M
//long blob 小于 4g


public class BlobTest {

    @Test
    public void test() throws Exception {
        Connection conn = jdbcUtil.getConnection();
        String sql = "insert into pet value(?,?,?,?)";
        PreparedStatement pre = conn.prepareStatement(sql);
        pre.setObject(1,"五光君");
        pre.setObject(2,"男");
        pre.setObject(3,"2017");
        FileInputStream fis = new FileInputStream("图片.jpg");
        pre.setBlob(4,fis);
        pre.execute();

        jdbcUtil.closeResoure(conn,pre);
    }

    @Test
    public void testQuery() throws Exception {
        Connection conn = jdbcUtil.getConnection();
        String sql = "select * from pet where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,2017);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String name = rs.getString(1);
            String sex = rs.getString(2);
            int id = rs.getInt(3);
            Blob photo = rs.getBlob(4);
            System.out.println(name + " " + sex + " " + id);
            InputStream is = photo.getBinaryStream();
            FileOutputStream fos = new FileOutputStream("/Users/wangshuxiao/Downloads/1.jpg");

            int len;
            byte[] buffer = new byte[10];
            while ((len = is.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            is.close();
        }

        jdbcUtil.closeResoure(conn,ps,rs);
    }
}
