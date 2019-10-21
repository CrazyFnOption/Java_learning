package com.language_learning.IO;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileInputOutputStream {

    // 对于文本文件 如果用字节流 容易发生乱码，所以最好使用字符流去读
    @Test
    public void testInput() {
        FileInputStream fis = null;
        try {
            File file = new File("hello.txt");
            fis = new FileInputStream(file);

            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                String str = new String(buffer, 0, len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    // 传入非文本类型的文件
    @Test
    public void test() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            File sourse = new File("/Users/wangshuxiao/Downloads/086adf20145547df81ae199acc27dcc0.jpeg");
            File dis = new File("图片.jpeg");

            fis = new FileInputStream(sourse);
            fos = new FileOutputStream(dis);

            byte[] buffer = new byte[5];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
