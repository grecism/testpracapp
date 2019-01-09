package com.main.http.commons.httpclient;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.IOException;

/**
 * @author  admin
 * @version 2018/5/11
 * @since 2018/5/11
 */
public class HttpClientTwo_01_Test {
    /**jar包**/
    //<!-- https://mvnrepository.com/artifact/commons-httpclient/commons-httpclient -->

    /**HttpClient简介**/
    //HTTP 协议可能是现在 Internet 上使用得最多、最重要的协议了，越来越多的 Java 应用程序需要直接通过 HTTP 协议来访问网络资源。虽然在 JDK 的 java.net 包中已经提供了访问 HTTP 协议的基本功能，
    //但是对于大部分应用程序来说，JDK 库本身提供的功能还不够丰富和灵活。HttpClient 是 Apache Jakarta Common 下的子项目，用来提供高效的、最新的、功能丰富的支持 HTTP 协议的客户端编程工具包，
    //并且它支持 HTTP 协议最新的版本和建议。HttpClient 已经应用在很多的项目中，比如 Apache Jakarta 上很著名的另外两个开源项目 Cactus 和 HTMLUnit 都使用了 HttpClient。
    // 更多信息请关注http://hc.apache.org/

    /**HttpClient功能介绍**/
    //以下列出的是 HttpClient 提供的主要的功能，要知道更多详细的功能可以参见 HttpClient 的主页。
    //实现了所有 HTTP 的方法（GET,POST,PUT,HEAD 等）
    //支持自动转向
    //支持 HTTPS 协议
    //支持代理服务器等

    /**应用HttpClient来对付各种顽固的WEB服务器**/
    //转自：http://blog.csdn.net/ambitiontan/archive/2006/01/06/572171.aspx
    //一般的情况下我们都是使用IE或者Navigator浏览器来访问一个WEB服务器，用来浏览页面查看信息或者提交一些数据等等。所访问的这些页面有的仅仅是一些普通的页面，有的需要用户登录后方可使用，或者需要认证以及
    //是一些通过加密方式传输，例如HTTPS。目前我们使用的浏览器处理这些情况都不会构成问题。不过你可能在某些时候需要通过程序来访问这样的一些页面，比如从别人的网页中“偷”一些数据；利用某些站点提供的页面来完
    //成某种功能，例如说我们想知道某个手机号码的归属地而我们自己又没有这样的数据，因此只好借助其他公司已有的网站来完成这个功能，这个时候我们需要向网页提交手机号码并从返回的页面中解析出我们想要的数据来。
    //如果对方仅仅是一个很简单的页面，那我们的程序会很简单，本文也就没有必要大张旗鼓的在这里浪费口舌。但是考虑到一些服务授权的问题，很多公司提供的页面往往并不是可以通过一个简单的URL就可以访问的，而必须
    //经过注册然后登录后方可使用提供服务的页面，这个时候就涉及到COOKIE问题的处理。我们知道目前流行的动态网页技术例如ASP、JSP无不是通过COOKIE来处理会话信息的。为了使我们的程序能使用别人所提供的服务页
    //面，就要求程序首先登录后再访问服务页面，这过程就需要自行处理cookie，想想当你用java.net.HttpURLConnection来完成这些功能时是多么恐怖的事情啊！况且这仅仅是我们所说的顽固的WEB服务器中的一个很常见
    //的“顽固”！再有如通过HTTP来上传文件呢？不需要头疼，这些问题有了“它”就很容易解决了！
    //我们不可能列举所有可能的顽固，我们会针对几种最常见的问题进行处理。当然了，正如前面说到的，如果我们自己使用java.net.HttpURLConnection来搞定这些问题是很恐怖的事情，因此在开始之前我们先要介绍一下一
    //个开放源码的项目，这个项目就是Apache开源组织中的httpclient，它隶属于Jakarta的commons项目，目前的版本是2.0RC2。commons下本来已经有一个net的子项目，但是又把httpclient单独提出来，可见http
    //服务器的访问绝非易事。
    //Commons-httpclient项目就是专门设计来简化HTTP客户端与服务器进行各种通讯编程。通过它可以让原来很头疼的事情现在轻松的解决，例如你不再管是HTTP或者HTTPS的通讯方式，告诉它你想使用HTTPS方式，剩下的事
    //情交给httpclient替你完成。本文会针对我们在编写HTTP客户端程序时经常碰到的几个问题进行分别介绍如何使用httpclient来解决它们，为了让读者更快的熟悉这个项目我们最开始先给出一个简单的例子来读取一个
    //网页的内容，然后循序渐进解决掉前进中的所有问题。

    /**HttpClient基本功能的使用**/
    //GET方法
    //使用HttpClient需要以下6个步骤：
    //(1)创建HttpClient的实例。
    //(2)创建某种连接方法的实例，在这里是GetMethod。在GetMethod的构造函数中传入待连接的地址。
    //(3)调用第一步中创建好的实例的execute方法来执行第二步中创建好的 method 实例。
    //(4)读response。
    //(5)释放连接，无论执行方法是否成功，都必须释放连接。
    //(6)对得到后的内容进行处理。
    public static void main(String[] args) {
        //构造HttpClient的实例
        HttpClient httpClient = new HttpClient();
        //创建GET方法的实例
        GetMethod getMethod = new GetMethod("https://www.baidu.com/");
        //使用系统提供的默认的恢复策略
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
        try {
            //执行getMethod
            int statusCode = httpClient.executeMethod(getMethod);
            if(statusCode != HttpStatus.SC_OK){
                System.out.println("Method failed:"+getMethod.getStatusLine());
            }
            //读取内容
            byte[] responseBody = getMethod.getResponseBody();
            //处理内容
            System.out.println(new String(responseBody));

        }catch (HttpException e) {
            //发生致命的异常，可能是协议不对或者返回的内容有问题
            System.out.println("Please check your provided http address!");
        }catch (IOException e) {
            //发生网络异常
            e.printStackTrace();
        }finally {
            //释放连接
            getMethod.releaseConnection();
        }
    }
}
