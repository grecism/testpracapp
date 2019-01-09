package com.main.http.httpurlconnection;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author admin
 * @version 2018/12/25
 * @since 2018/12/25
 */
public class HttpClientOne_02_Test {
    public static String doGet(String path){
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            URL url = new URL(path.trim());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if(connection.getResponseCode() == 200){
                is = connection.getInputStream();
                bos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                if((len = is.read()) != -1){
                    bos.write(buffer,0,len);
                    bos.flush();
                }
                bos.close();
                is.close();
                return bos.toString("utf-8");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String doPost(String path,String param){
        PrintWriter pw = null;
        try {
            URL url = new URL(path.trim());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(13000);
            connection.setReadTimeout(50000);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            pw = new PrintWriter(connection.getOutputStream());
            pw.write(param);
            pw.flush();
            BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            if((len = bis.read()) !=- 1){
                bos.write(buffer,0,len);
                bos.flush();
            }
            bos.close();
            bis.close();
            pw.close();
            return bos.toString("utf-8");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
