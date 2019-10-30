package com.language_learning.IO;

import org.junit.Test;

import java.io.*;

/*
    1。要想满足自定义类型序列化的话，需要满足 相应的表示接口 Serializable

    2。而且这个类里面的类或者属性 也必须要保证可以序列化的

    3。ObjectOutputStream 与 ObjectInputStream 不能用来修饰static 或者transient（让其不被序列话）这两个修饰符
 */

public class ObjectStreamTest implements Serializable{

    // 当想申明一个类为 可序列号的接口的话 必须要有一个全局常量 uid
    // 主要就是靠这个数据来进行识别
    public static final long serialVersionUID = 421231231;
    @Test
    public void testObject() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ObjectOutputStream.dat"));
        oos.writeObject(new String("我爱天安门"));
        // 这个flush 是必须存在的
        oos.flush();
        if (oos != null) oos.close();
    }

    @Test
    public void testinput() throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ObjectOutputStream.dat"));
        Object obj = ois.readObject();
        System.out.println(obj);
        ois.close();
    }
}

