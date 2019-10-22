package com.language_learning.IO;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1.标准输入输出流
// 2.打印流 printstream and printwriter 基本上应用于重定向的里面
// 3.数据流 datainputstream and dataoutputstream
// 其实这个就相当于可以读进去相应的数据类型的 Scanner 就相当于是包装了这一个东西
// 这里就不做过多声明了，就相当于一个小的Scanner类
public class OtherStream {

    @Test
    public void test1() throws IOException {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String data;
        while (true) {
            data = br.readLine();
            if (data.equalsIgnoreCase("e") || data.equalsIgnoreCase("exit")) {
                System.out.println("程序结束");
                break;
            }
            String upper = data.toUpperCase();
            System.out.println(upper);
        }
        br.close();
    }
}
