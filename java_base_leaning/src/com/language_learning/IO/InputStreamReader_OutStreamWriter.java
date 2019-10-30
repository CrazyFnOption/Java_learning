package com.language_learning.IO;


import org.junit.Test;

import java.io.*;

/*

    就在这里总结一下几种字符编码
    ascii 美国标准信息交换码
    ISO8859-1 拉丁码表 欧洲码表
    GV2312 中国的中文编码 最多两个字节编码所有字符
    GBK 中国的中文编码升级，融合了更多的中文文字富豪
    Unicode 国际标准码 融合了日常人类使用的所有字符，为每一个字符分配唯一的字符码。
    UTF-8 变长的编码方式，可用1-4字节来表示一个字符

 */
// InputStreamReader 将字节流转换成字符流，相当于解码操作
// OutStreamWriter 将字符流转换成字节流，相当于压缩操作
public class InputStreamReader_OutStreamWriter {
    @Test
    public void test() {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        InputStreamReader isr = null;
        OutputStreamWriter ops = null;
        try {
            File sourse = new File("/Users/wangshuxiao/Downloads/086adf20145547df81ae199acc27dcc0.jpeg");
            File dis = new File("图片.jpeg");

//              FileReader FileWriter 也是一样的写法
//              缓冲流就是用来加速的，
//              字符流加速的时候
//              可以用string 然后用readline

//              节点流
            fis = new FileInputStream("hello.txt");
            fos = new FileOutputStream(dis);

            // 处理流 传输过程中进行处理，并且加快速度
            isr = new InputStreamReader(fis);
            ops = new OutputStreamWriter(fos);

            char[] buffer = new char[10];
            int len;
            while ((len = isr.read(buffer)) != -1) {
//
                String str = new String(buffer,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                ops.close();
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
