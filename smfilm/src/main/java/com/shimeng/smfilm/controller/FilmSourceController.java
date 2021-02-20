package com.shimeng.smfilm.controller;

import com.alibaba.fastjson.JSONObject;
import com.shimeng.smfilm.common.BaseResp;
import com.shimeng.smfilm.common.StatusMessage;
import com.shimeng.smfilm.model.entity.FilmSource;
import com.shimeng.smfilm.model.req.QueryVideoUrlReq;
import com.shimeng.smfilm.model.resp.MuObject;
import com.shimeng.smfilm.service.FilmSourceService;
import com.shimeng.smfilm.utils.SslUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/filmSource")
public class FilmSourceController {
    private Logger logger = LoggerFactory.getLogger(ChannelController.class);

    @Resource
    private FilmSourceService filmSourceService;

    @PostMapping(value = "/queryVideoUrl")
    public BaseResp<MuObject> queryVideoUrl(@RequestBody QueryVideoUrlReq req) {
        BaseResp<MuObject> result = new BaseResp<>(StatusMessage.SUCCESS);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        MuObject muObject = null;
        try {
            FilmSource filmSource = filmSourceService.getFilmSource(req.getFid(), req.getSid().trim());
            String url = req.getChannelUrl() + filmSource.getFilmUrl();
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpResponse response = null;

            try {
                String source = getRequest(url, 3000);

                // 由客户端执行(发送)Get请求
                //response = httpClient.execute(httpGet);
                // 从响应模型中获取响应实体
                //HttpEntity responseEntity = response.getEntity();
                //System.out.println("响应状态为:" + response.getStatusLine());
                if (!"".equals(source)) {
//                    System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                    long contentLength = responseEntity.getContentLength();
//                    String source = EntityUtils.toString(responseEntity);
//                    System.out.println("响应内容为:" + source);

                    // 为-1 则返回为html版
                    if (source.contains("html")){
                        List<String> list = match(source, "iframe", "src");
                        String[] split = list.get(0).split("=");
                        muObject = new MuObject();
                        muObject.setUrl(split[1]);
                        muObject.setType("m3u8");
                    } else {
                        muObject = JSONObject.parseObject(source, MuObject.class);
                    }
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    // 释放资源
                    if (httpClient != null) {
                        httpClient.close();
                    }
                    if (response != null) {
                        response.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            //  String resultUrl = HttpRequestClient.getInstance().doGet(url, null, null);
            return result.setData(muObject);
        } catch (Exception e) {
            logger.error("saveFilm err ", e);
            return result.setStatusMessage(StatusMessage.ERROR);
        }

    }

    /**
     * 获取指定HTML标签的指定属性的值
     *
     * @param source  要匹配的源文本
     * @param element 标签名称
     * @param attr    标签的属性名称
     * @return 属性值列表
     */
    public static List<String> match(String source, String element, String attr) {
        List<String> result = new ArrayList<String>();
        String reg = "<" + element + "[^<>]*?\\s" + attr + "=['\"]?(.*?)['\"]?(\\s.*?)?>";
        Matcher m = Pattern.compile(reg).matcher(source);
        while (m.find()) {
            String r = m.group(1);
            result.add(r);
        }
        return result;
    }

    /**
     * get 请求封装
     * @param url
     * @param timeOut
     * @return
     * @throws Exception
     */
    public String getRequest(String url,int timeOut) throws Exception{
        URL u = new URL(url);
        if("https".equalsIgnoreCase(u.getProtocol())){
            SslUtils.ignoreSsl();
        }
        URLConnection conn = u.openConnection();
        conn.setConnectTimeout(timeOut);
        conn.setReadTimeout(timeOut);
        return IOUtils.toString(conn.getInputStream());
    }

    /**
     * post 请求封装
     * @param urlAddress
     * @param args
     * @param timeOut
     * @return
     * @throws Exception
     */
    public String postRequest(String urlAddress,String args,int timeOut) throws Exception{
        URL url = new URL(urlAddress);
        if("https".equalsIgnoreCase(url.getProtocol())){
            SslUtils.ignoreSsl();
        }
        URLConnection u = url.openConnection();
        u.setDoInput(true);
        u.setDoOutput(true);
        u.setConnectTimeout(timeOut);
        u.setReadTimeout(timeOut);
        OutputStreamWriter osw = new OutputStreamWriter(u.getOutputStream(), "UTF-8");
        osw.write(args);
        osw.flush();
        osw.close();
        u.getOutputStream();
        return IOUtils.toString(u.getInputStream());
    }
}
