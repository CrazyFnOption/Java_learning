package com.language_learning.Internet;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

// 这里是通信的相关协议，udp tcp相关协议
// 而下面的shutdowninput 或者output就是相当于给一个信号，让其关闭的一个信号。
public class TCP3 {

    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        InputStream is = null;
        FileInputStream fis = null;
        BufferedInputStream bfis = null;
        ByteArrayOutputStream bos = null;
        try {
            InetAddress inetAddress = InetAddress.getByName("localhost");
            socket = new Socket(inetAddress,9900);
            os = socket.getOutputStream();
            fis = new FileInputStream("/Users/wangshuxiao/Downloads/u=4204208741,2703435161&fm=26&gp=0.jpg");
            bfis = new BufferedInputStream(fis);
            int len;
            byte[] buffer = new byte[10];
            while ((len = bfis.read(buffer)) != -1) {
                os.write(buffer,0,len);
            }
            socket.shutdownOutput();

            is = socket.getInputStream();
            bos = new ByteArrayOutputStream();
            len = 0;
            while ((len = is.read(buffer)) != -1) {
                bos.write(buffer,0,len);
            }
            System.out.println(bos);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bfis != null) {
                try {
                    bfis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

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
        OutputStream os = null;
        FileOutputStream fos = null;
        BufferedOutputStream bfos = null;
        try {
            ss = new ServerSocket(9900);
            socket = ss.accept();

            is = socket.getInputStream();
            fos = new FileOutputStream("图片.jpg");
            bfos = new BufferedOutputStream(fos);
            int len;
            byte[] buffer = new byte[10];
            while ((len = is.read(buffer)) != -1) {
                bfos.write(buffer,0,len);
            }
            socket.shutdownInput();

            os = socket.getOutputStream();
            os.write("收到，收到，谢谢主机的信任".getBytes());
            socket.shutdownOutput();


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
            if (bfos != null) {
                try {
                    bfos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (fos != null) {
                try {
                    fos.close();
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
