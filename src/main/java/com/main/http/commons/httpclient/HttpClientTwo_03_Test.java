package com.main.http.commons.httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.IOException;

/**
 * @author  admin
 * @version 2018/5/11
 * @since 2018/5/11
 */
public class HttpClientTwo_03_Test {
    /**blog**/
    //https://www.cnblogs.com/ITtangtang/p/3968093.html

    /**读取网页(HTTP/HTTPS)内容**/
    //一个简单的例子用来访问某个页面。
    //最简单的HTTP客户端,用来演示通过GET或者POST方式访问某个页面。
    //在这个例子中首先创建一个HTTP客户端(HttpClient)的实例，然后选择提交的方法是GET或者POST，最后在HttpClient实例上执行提交的方法，最后从所选择的提交方法中读取服务器反馈回来的结果。这就是使用HttpClient的基本流程。
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new HttpClient();
        //设置代理服务器地址和端口
        //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
        //使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https
        HttpMethod httpMethod = new GetMethod("http://java.sun.com");
        //使用POST方法
        //HttpMethod httpMethod = new PostMethod("http://java.sun.com");
        httpClient.executeMethod(httpMethod);
        //打印服务器返回的状态
        System.out.println(httpMethod.getStatusLine());
        //打印返回的信息
        System.out.println(httpMethod.getResponseBodyAsString());
        //释放连接
        httpMethod.releaseConnection();

    }
}
