package com.sqc95111.datasynchronize.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/3/27 11:08
 * @Version 1.0
 */
@Component
public class HttpSpringRestTemplate {
    private RestTemplate restTemplate;
    private static Logger logger = LoggerFactory.getLogger(HttpSpringRestTemplate.class);

    public RestTemplate getRestTemplate(){
        if(this.restTemplate==null){
            return  new RestTemplate();
        }
        return restTemplate;
    }

    public static URL URL(String url) throws MalformedURLException {
        return new URL(url);
    }

    public  String postWithHeader(String url, Map<String,String> paramMap,Map<String,String> header) throws MalformedURLException {
        RestTemplate restTemplate = getRestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        for(Map.Entry<String,String> entry: header.entrySet()){
            headers.add(entry.getKey(), entry.getValue());
        }
        HttpEntity<String> formEntity = new HttpEntity<>(null, headers);
        String response = restTemplate.postForObject(url, formEntity,String.class, paramMap);
        return response;
    }


    public  String getWithHeader(String url,Map<String,String> header)  {
        HttpHeaders requestHeaders = new HttpHeaders();
        for(Map.Entry<String,String> entry: header.entrySet()){
            requestHeaders.add(entry.getKey(), entry.getValue());
        }
        RestTemplate template = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        ResponseEntity<String> response = template.exchange(url, HttpMethod.GET, requestEntity, String.class);
        String sttr = response.getBody();
        return sttr;
    }




    private  HttpSpringRestTemplate() {
    }


    public static void main(String[] args) throws MalformedURLException {
        String url = "http://best-eagle.sqc95111.com/express-md/restful/restfulPkgBillCodeEProvideService/searchPkgBillCodeEProvideVO";
        Map<String,String> headers = new HashMap<>();
        headers.put("Content-Type", "[{\"key\":\"Content-Type\",\"name\":\"Content-Type\",\"value\":\"application/json\",\"description\":\"\",\"type\":\"text\"}]");
        headers.put("token", "system_job_token_sqc95111_q9xingng_systemJobHandler");
        headers.put("openId","auto");
        headers.put("openSecure","eagle_auto");
        HttpSpringRestTemplate springRestTemplate = new HttpSpringRestTemplate();
        Map<String,String> map = new HashMap<>();
        map.put("pkgBillCodeEProvideSo", "{\"pkgCode\":\"402000005172\"}");
        String response = springRestTemplate.postWithHeader(url,map , headers);
        logger.info(response);
        System.out.println(response);
    }






}
