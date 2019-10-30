package com.language_learning.Internet;

import java.net.InetAddress;
import java.net.UnknownHostException;

// 本机的地址 127.0.0.1 其实对应的就是localhost
// 域名就是区分不同主机 而端口号就是 区分主机上面的不同进程
// 端口号与IP地址组合成一个网络套接字 Socket
public class InernetAdderessTest {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inet1= InetAddress.getByName("192.168.10.14");

        System.out.println(inet1);
        InetAddress inet2 = InetAddress.getByName("www.baidu.com");
        System.out.println(inet2);
        // 这个类就是专门获取dns解析域名
        System.out.println(InetAddress.getLocalHost());

    }
}
