package com.main.http.httpclient.hccommons;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.methods.GetMethod;
import java.io.IOException;

/**
 * @author  admin
 * @version 2018/5/11
 * @since 2018/5/11
 */
public class HcCommons_06_Test {
    /**访问启用认证的页面**/
    //我们经常会碰到这样的页面，当访问它的时候会弹出一个浏览器的对话框要求输入用户名和密码后方可，这种用户认证的方式不同于我们在前面介绍的基于表单的用户身份验证。这是HTTP的认证策略，
    //httpclient支持三种认证方式包括：基本、摘要以及NTLM认证。其中基本认证最简单、通用但也最不安全；摘要认证是在HTTP 1.1中加入的认证方式，而NTLM则是微软公司定义的而不是通用的规范，
    //最新版本的NTLM是比摘要认证还要安全的一种方式。

    /**多线程模式下使用**/
    //多线程同时访问httpclient，例如同时从一个站点上下载多个文件。对于同一个HttpConnection同一个时间只能有一个线程访问，为了保证多线程工作环境下不产生冲突，httpclient使用了一个多线程连接管理器的类：
    //MultiThreadedHttpConnectionManager，要使用这个类很简单，只需要在构造HttpClient实例的时候传入即可，代码如下：
    //MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();
    //HttpClient client = new HttpClient(connectionManager);
    //以后尽管访问client实例即可。
    public static void main(String[] args) throws IOException {
        HttpClient httpClient = new HttpClient();
        httpClient.getState().setCredentials("url","realm",new UsernamePasswordCredentials("xxx","123"));
        GetMethod getMethod = new GetMethod("url");
        getMethod.setDoAuthentication(true);
        int statusCode = httpClient.executeMethod(getMethod);
        System.out.println(statusCode+"\n"+getMethod.getResponseBodyAsString());
        getMethod.releaseConnection();
    }
}
