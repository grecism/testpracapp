package com.main.http.httpclient.hccommons;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang3.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author  admin
 * @version 2018/5/11
 * @since 2018/5/11
 */
public class HcCommons_04_Test {
    /**使用POST方式提交数据(httpClient3)**/
    //httpclient使用了单独的一个HttpMethod子类来处理文件的上传，这个类就是MultipartPostMethod，该类已经封装了文件上传的细节，我们要做的仅仅是告诉它我们要上传文件的全路径即可，两种模拟上传的方式。
    //第一种：模拟上传url文件(该方式也适合做普通post请求)
    public static void main(String[] args) {
        String postUrl = "";
        try {
            String data = doUploadFile(postUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传url文件到指定URL
     * @param postUrl 上传路径及参数,注意有些中文参数需要使用预先编码 eg : URLEncoder.encode(appName, "UTF-8") fileUrl 上传图片url
     * @return
     * @throws IOException
     */
    private static String doUploadFile(String postUrl) throws IOException {
        if(StringUtils.isEmpty(postUrl))
            return null;
        String response = "";
        HttpClient httpClient = new HttpClient();
        //设置连接时间
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(50000);
        PostMethod postMethod = new PostMethod(postUrl);

        try {
            int statusCode = httpClient.executeMethod(postMethod);
            if(statusCode == HttpStatus.SC_OK){
                InputStream inputStream = postMethod.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer sb = new StringBuffer();
                String str = "";
                while((str = br.readLine()) != null){
                    sb.append(str);
                }
                response = sb.toString();
            }else{
                response = "fail";
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }
        return response;
    }
}
