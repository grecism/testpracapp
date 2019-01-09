package com.main.http.httpurlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;

/**
 * @author admin
 * @version 2018/12/25
 * @since 2018/12/25
 */
public class HttpClientOne_01_Test {
    //java实现HTTP请求的三种方式。
    //目前JAVA实现HTTP请求的方法用的最多的有两种：一种是通过HTTPClient这种第三方的开源框架去实现。HTTPClient对HTTP的封装性比较不错，通过它基本上能够满足我们大部分的需求，HttpClient3.1 是 org.apache.commons.httpclient下操作远程 url的工具包，虽然已不再更新，
    //但实现工作中使用httpClient3.1的代码还是很多，HttpClient4.5是org.apache.http.client下操作远程 url的工具包，最新的；另一种则是通过HttpURLConnection去实现，HttpURLConnection是JAVA的标准类，是JAVA比较原生的一种实现方式。
    //自己在工作中三种方式都用到过，总结一下分享给大家，也方便自己以后使用，话不多说上代码。
    //第一种方式：java原生HttpURLConnection
    public static String doGet(String httpUrl){
            HttpURLConnection connection = null;
            InputStream is = null;
            BufferedReader br = null;
            //返回结果字符串。
            String result = null;
        try {
            //创建远程url链接对象。
            URL url = new URL(httpUrl);
            //通过远程url链接对象打开一个链接，强转成HttpURLConnection类。
            connection = (HttpURLConnection) url.openConnection();
            //设置链接方式：get。
            connection.setRequestMethod("GET");
            //设置链接主机服务器的超时时间：15000毫秒。
            connection.setConnectTimeout(15000);
            //设置读取远程返回的数据时间：60000毫秒。
            connection.setReadTimeout(60000);
            //发送请求。
            connection.connect();
            //通过链接，获取输入流。
            if(connection.getResponseCode() == 200){
                is = connection.getInputStream();
                //封装输入流，并指定字符集。
                br = new BufferedReader(new InputStreamReader(is,"utf-8"));
                //存放数据。
                StringBuffer sbf = new StringBuffer();
                String temp  = null;
                while((temp = br.readLine()) != null){
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭资源。
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //关闭远程链接。
            connection.disconnect();
        }
        return result;
    }

    public static String doPost(String httpUrl,String param){
        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;

        try {
            URL url = new URL(httpUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(15000);
            connection.setReadTimeout(60000);
            //默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true。
            //设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内。
            connection.setDoOutput(true);
            //默认值为：true，当向远程服务器读取数据时，设置为true，该参数可有可无。
            //设置是否从httpUrlConnection读入，默认情况下是true。
            connection.setDoInput(true);
            //设置传入参数的格式：请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // Post 请求不能使用缓存
            //connection.setUseCaches(false);
            //设定传送的内容类型是可序列化的java对象(如果不设此项，在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException) 。
            //connection.setRequestProperty("Content-type", "application/x-java-serialized-object");
            //设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0。
            //connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            //连接，从url.openConnection()至此的配置必须要在connect之前完成。
            //connection.connect();
            //通过链接对象获取一个输出流。
            //此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法， 所以在开发中不调用上述的connect()也可以)。
             os = connection.getOutputStream();
            //通过输出流对象将参数写出去/传输出去，它是通过字节数组写出的。
             os.write(param.getBytes());
            if(connection.getResponseCode() == 200){
                is = connection.getInputStream();
                br = new BufferedReader(new InputStreamReader(is,"utf-8"));
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                if((temp =br.readLine()) != null){
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭资源。
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            connection.disconnect();
        }
        return result;
    }
}
