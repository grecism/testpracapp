package com.main.http.commons.httpclient;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;

/**
 * @author  admin
 * @version 2018/5/11
 * @since 2018/5/11
 */
public class HttpClientTwo_02_Test {
    //POST方法
    public static void main(String[] args) {
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        String url = "url";
        PostMethod postMethod = new PostMethod(url);
        //填入各个表单域的值
        NameValuePair[] data = {new NameValuePair("text","xxx"),new NameValuePair("password","123")};
        //将表单的值放入postMethod中
        postMethod.setRequestBody(data);
        try {
            //执行postMethod
            int statusCode = httpClient.executeMethod(postMethod);
            System.out.println("statusCode="+statusCode);
            //HttpClient对于要求接受后继服务的请求，象POST和PUT等不能自动处理转发
            //301或者302
            if(statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY){
            //从头中取出转向的地址
                Header locationHeader = postMethod.getResponseHeader("location");
                String location = null;
                if(locationHeader != null){
                    location = locationHeader.getValue();
                    System.out.println("The page was redirected to:" + location);
                }else {
                    System.err.println("Location field value is null.");
                }

                return;
            }

        } catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
        }catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        }finally {
            //释放连接
            postMethod.releaseConnection();
        }

    }
}
