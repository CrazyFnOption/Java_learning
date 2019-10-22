package com.language_learning.Internet;

import javax.net.ssl.HttpsURLConnection;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

// 这里就是直接可以用相应的格式，直接从网络上面下载，当然这个只是一个小小的功能

public class URLTest {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://img3.doubanio.com/view/group_topic/l/public/p218216574.jpg");
        HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();

        httpsURLConnection.connect();
        InputStream is = httpsURLConnection.getInputStream();
        FileOutputStream fos = new FileOutputStream("tupian.jpg");
        byte[] buffer = new byte[10];
        int len;
        while ((len = is.read(buffer)) != -1) {
            fos.write(buffer,0,len);
        }
        fos.close();
        is.close();
        httpsURLConnection.disconnect();
    }
}
