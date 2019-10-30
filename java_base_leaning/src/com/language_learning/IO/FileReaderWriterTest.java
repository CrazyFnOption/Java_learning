package com.language_learning.IO;

import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

// 这个地方需要注意的是在@Test 单元测试格里面与主函数里面的 相对路径是不一样的。
// 如果有多个module 在主函数里面最好要指明是哪一个。
// 读入的文件 一定要存在，要不然一定会报异常
// 字符流是不能处理非字符型的文件
public class FileReaderWriterTest {

    @Test
    public void test()  {
        FileReader fileReader = null;
        try {
            File file = new File("hello.txt");
            fileReader = new FileReader(file);

//        int data = fileReader.read();
//        while (data != -1) {
//            System.out.print((char)data);
//            data = fileReader.read();
//        }

            int data;
            while ((data = fileReader.read()) != -1) {
                System.out.print((char)data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null)
                    fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test2() throws Exception {
        File file = new File("hello.txt");
        FileReader fileReader = new FileReader(file);
        char[] buffer = new char[8];
        int len;
        while ((len = fileReader.read(buffer)) != -1) {
            String s = new String(buffer,0, len);
            System.out.print(s);
        }
        fileReader.close();
        // 然后上面返回值返回的其实是一个读进去的个数而已。
        //多次循环操作的话，也是对上面的进行一个操作覆盖
        //基本上进行的操作就是使用string来进行操作
    }

    @Test
    public void testWirter() throws Exception {
        File file = new File("hello1.txt");
        //这里的true主要就是体现在 能否往后面进行append。
        FileWriter fw = new FileWriter(file,true);
        fw.write("I have a dream!");
        fw.write("\nyou need a dream!");
        //fw.append("\nmany people do not have some dreams!");
        fw.close();
    }
}
