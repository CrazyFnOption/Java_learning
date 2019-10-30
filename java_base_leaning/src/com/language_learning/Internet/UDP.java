package com.language_learning.Internet;

import org.junit.Test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP {
    @Test
    public void send() throws IOException {
        DatagramSocket socket = new DatagramSocket();
        String str = "导弹发射";
        byte[] buffer = str.getBytes();
        InetAddress inet = InetAddress.getLocalHost();
        DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length,inet,9090);
        socket.send(packet);
        socket.close();
    }

    @Test
    public void receive() throws IOException {
       DatagramSocket socket = new DatagramSocket(9090);
       byte[] buffer = new byte[100];
       DatagramPacket packet = new DatagramPacket(buffer,0,buffer.length);
       socket.receive(packet);
       System.out.println(new String(packet.getData(),0,packet.getLength()));
       socket.close();
    }
}