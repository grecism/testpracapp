package com.main.http.commons.httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author  admin
 * @version 2018/5/11
 * @since 2018/5/11
 */
public class HttpClientTwo_05_Test {
    //第二种：模拟文件上传到指定位置
    public static void main(String[] args) {
        File file = new File("");
        String postUrl = "";
        try {
            String data = doUploadFile(file,postUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传文件到指定URL
     * @param file
     * @param postUrl
     * @return
     * @throws IOException
     */
    private static String doUploadFile(File file, String postUrl) throws IOException{
        if(!file.exists()){
            return "file not exists";
        }
        String response = "";
        HttpClient httpClient = new HttpClient();
        //由于要上传的文件可能比较大，因此在此设置最大的连接超时时间
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(50000);
        PostMethod postMethod = new PostMethod(postUrl);

        try {
            //FilePart：用来上传文件的类,file即要上传的文件
            FilePart fp = new FilePart("file",file);
            Part[] parts = {fp};
            //对于MIME类型的请求，httpclient建议全用MulitPartRequestEntity进行包装
            MultipartRequestEntity mre = new MultipartRequestEntity(parts,postMethod.getParams());
            postMethod.setRequestEntity(mre);
            int statusCode = httpClient.executeMethod(postMethod);
            if (statusCode == HttpStatus.SC_OK) {
                InputStream inputStream = postMethod.getResponseBodyAsStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer = new StringBuffer();
                String str = "";
                while ((str = br.readLine()) != null) {
                    stringBuffer.append(str);
                }
                response = stringBuffer.toString();
            } else {
                response = "fail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放连接
            postMethod.releaseConnection();
        }

        return null;
    }
}
