package com.sqc95111.datasynchronize;

import com.sqc95111.datasynchronize.http.Result;
import com.sqc95111.datasynchronize.util.HttpSpringRestTemplate;
import com.sqc95111.datasynchronize.util.JsonMapper;
import com.fasterxml.jackson.databind.JavaType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/4/3 15:37
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpVisitDiamond {

    private static HttpServletRequest httpServletRequest;
    private static HttpSpringRestTemplate httpSpringRestTemplate;
    private static String url = "http://diamond.appl.sqc95111.com/";
    static{
        httpServletRequest.setAttribute("Cookie", "JSESSIONID=9567AF48897213E3CF4A2EB794E89DCF; SPRING_SECURITY_REMEMBER_ME_COOKIE=QkczNzczNTA6MTU1NjY5NDkzOTYzNzo4ZTY2ZDJiYjQzYWZkNjE4MTNmOTk5ZjIzMDQ3ZTlkOA");
        httpServletRequest.setAttribute("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
    }

    @Test
    public void goGetSiteCode() throws MalformedURLException {
        String backUrl = "/biz/config/query?limit=100&offset=0&sort=createTime&order=desc&projectId=316&profileId="+932;
        String addUrl = url+backUrl;
        Map<String,String> map  = new HashMap<>();
        map.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        map.put("Cookie", "JSESSIONID=9567AF48897213E3CF4A2EB794E89DCF; SPRING_SECURITY_REMEMBER_ME_COOKIE=QkczNzczNTA6MTU1NjY5NDkzOTYzNzo4ZTY2ZDJiYjQzYWZkNjE4MTNmOTk5ZjIzMDQ3ZTlkOA");
        List<Result> results = new ArrayList<>();
        String reponse =  httpSpringRestTemplate.postWithHeader(addUrl,null,map);
        JsonMapper mapper = JsonMapper.nonDefaultMapper();
        JavaType javaType = mapper.createCollectionType(ArrayList.class, Result.class);
        results = mapper.fromJson(reponse, javaType);
        if(!CollectionUtils.isEmpty(results)){
            System.out.println(results.get(5).getConfigValue());
        }
        System.out.println("null");
    }
}
