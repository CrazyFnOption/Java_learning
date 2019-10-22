package com.language_learning.IO;

// 这个类存在的最大的意义就是复制的过程中提升相应

import org.junit.Test;

import java.io.*;

public class BufferedTest {

    @Test
    public void test() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        BufferedInputStream bfis = null;
        BufferedOutputStream bfos = null;
        try {
            File sourse = new File("/Users/wangshuxiao/Downloads/086adf20145547df81ae199acc27dcc0.jpeg");
            File dis = new File("图片.jpeg");

//              FileReader FileWriter 也是一样的写法
//              缓冲流就是用来加速的，
//              字符流加速的时候
//              可以用string 然后用readline

//              节点流
            fis = new FileInputStream(sourse);
            fos = new FileOutputStream(dis);

            // 处理流 传输过程中进行处理，并且加快速度
            bfis = new BufferedInputStream(fis);
            bfos = new BufferedOutputStream(fos);

            byte[] buffer = new byte[5];
            int len;
            while ((len = bfis.read(buffer)) != -1) {
                bfos.write(buffer, 0, len);
//                刷新缓冲区的意思
//                bfos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                bfis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                bfos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
