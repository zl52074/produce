package com.zhangle.produce.common.util;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description: 封装HttpClient
 * @author: zl52074
 * @time: 2020/3/22 18:22
 */
@Component
public class HttpUtils {

    private PoolingHttpClientConnectionManager cm;

    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        this.cm.setMaxTotal(100);
        //设置每个访问的主机的最大连接数
        this.cm.setDefaultMaxPerRoute(10);
    }


    /**
     * @description 根据请求地址下载html
     * @param url
     * @return java.lang.String
     * @author zl52074
     * @time 2020/3/22 18:40
     */
    public String doGetHtml(String url){
        //获取HttpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(this.cm).build();
        //创建httpGet请求对象，设置url地址
        HttpGet httpGet = new HttpGet(url);
        //模拟真实浏览器，防止403被禁止
        httpGet.addHeader("Accept", "text/html");
        httpGet.addHeader("Accept-Charset", "utf-8");
        httpGet.addHeader("Accept-Encoding", "gzip");
        httpGet.addHeader("Accept-Language", "en-US,en");
        httpGet.addHeader("User-Agent",
                "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");
        //设置请求信息
        httpGet.setConfig(this.getConfig());

        //使用HttpClient发起请求，获取响应
        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode()==200){
                //判断响应体Entity是否不为空
                if(response.getEntity()!=null){
                    String content = EntityUtils.toString(response.getEntity(), "utf8");
                    return content;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭response
            if(response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //响应体没有数据返回空串
        return "";
    }


    /**
     * @description request设置
     * @param
     * @return org.apache.http.client.config.RequestConfig
     * @author zl52074
     * @time 2020/3/22 18:55
     */
    private RequestConfig getConfig() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(60*1000)  //创建连接的最长时间
                .setConnectionRequestTimeout(60*1000)  //获取连接的最长时间
                .setSocketTimeout(300*1000)  //数据传输的最长时间
                .build();
        return requestConfig;
    }

}
