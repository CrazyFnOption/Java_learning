package com.language_learning.Internet;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP1 {

    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            socket = new Socket(inetAddress,9900);
            os = socket.getOutputStream();
            os.write("客户端1号，向你问好".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



    }

    @Test
    public void server() {
        ServerSocket ss = null;
        Socket socket = null;
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            ss = new ServerSocket(9900);
            socket = ss.accept();

            is = socket.getInputStream();
            bos = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[10];
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer,0,len);
            }
            System.out.println(bos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
