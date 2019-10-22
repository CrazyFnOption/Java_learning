package com.language_learning.Internet;

import org.junit.Test;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP2 {

    @Test
    public void client() {
        Socket socket = null;
        OutputStream os = null;
        FileInputStream fis = null;
        BufferedInputStream bfis = null;
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
