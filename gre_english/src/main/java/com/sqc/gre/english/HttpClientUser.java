package com.sqc.gre.english;

import org.apache.http.Consts;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/3/26 16:36
 * @Version 1.0
 */
@Component
public class HttpClientUser {

    public static String postWithHeader(String url, Map<String,String> paramMap,Map<String,String> headers) throws IOException {

        String responseBody = null;
        //创建CloseableHttpClient
        HttpClientBuilder builder = HttpClientBuilder.create();
        CloseableHttpClient client = builder.build();
        //执行
        HttpUriRequest httpPost = new HttpPost(url);
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (paramMap != null) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        ((HttpPost) httpPost).setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        CloseableHttpResponse response = client.execute(httpPost);
        if(response!=null) {
            responseBody = EntityUtils.toString(response.getEntity());
        }
        return responseBody;
    }









    public static Map<String, String> asMap(String... kvs) {
        Map<String, String> map = new HashMap<String, String>();
        if (kvs != null && kvs.length > 0 && kvs.length % 2 == 0) {
            for (int i = 0; i < kvs.length; i += 2) {
                map.put(kvs[i], kvs[i + 1]);
            }
        }
        return map;
    }


    public static void main(String[] args) throws IOException {
        String url = "http://sqc95111.com/express-md/restful/restfulPkgBillCodeEProvideService/searchPkgBillCodeEProvideVO";
//        url = url + "?pkgBillCodeEProvideSo={pkgBillCodeEProvideSo}";
//        url = url + "?pkgBillCodeEProvideSo="+param;
        Map<String,String> headers = new HashMap<>();
        headers.put("token", "system_job_token_sqc95111_q9xingng_systemJobHandler");
        headers.put("openId","auto");
        headers.put("openSecure","eagle_auto");
        String response = postWithHeader(url, asMap("pkgBillCodeEProvideSo", "{\"pkgCode\":\"402000005172\"}"),headers);
        Object pack = JsonMapper.nonDefaultMapper().fromJson(response, Object.class);
        System.out.println(pack);
    }
}

