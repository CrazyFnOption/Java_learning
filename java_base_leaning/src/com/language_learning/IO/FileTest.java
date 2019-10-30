package com.language_learning.IO;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class FileTest {

    @Test
    public void test() {
        File file = new File("hello.txt");
        System.out.println(file);

        File file1 = new File("src","h1.txt");
        System.out.println(file1);

        File file2 = new File(file1,"hee.txt");
        System.out.println(file2);

        System.out.println(file.getAbsoluteFile());
        System.out.println(file.length());
        System.out.println(file.lastModified());

        System.out.println(file.isDirectory());
        System.out.println(file.exists());

    }

    // 关于创建文件目录的话 使用的是mkdir 或者 是mkdirs 前者是不创建上级目录，后者就是创建上级目录
    // 这个就是顶多就是创建多级文件夹，所以这里不做过多解释
    @Test
    public void test1() throws IOException {
        File file = new File("hello.txt");
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("创建成功。");
        }
        else {
            file.delete();
            System.out.println("删除成功");
        }
    }
}
