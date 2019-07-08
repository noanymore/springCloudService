package com.sqc95111.datasynchronize;


import com.sqc95111.datasynchronize.http.Result;
import com.sqc95111.datasynchronize.mapper.DataSynchronizationMapper;
import com.sqc95111.datasynchronize.model.DataSynchronization;
import com.sqc95111.datasynchronize.service.SqlService;
import com.sqc95111.datasynchronize.thread.MyDataSynThread;
import com.sqc95111.datasynchronize.util.HttpClientUser;
import com.sqc95111.datasynchronize.util.HttpSpringRestTemplate;
import com.sqc95111.datasynchronize.util.JsonMapper;
import com.fasterxml.jackson.databind.JavaType;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSynchronizeApplicationTests {

    @Resource
    HttpClientUser httpClientUser;

    @Resource
    HttpSpringRestTemplate httpSpringRestTemplate;

    @Resource
    DataSynchronizationMapper dataSynchronizationMapper;


    @Resource
    SqlService sqlService;

    public static Logger logger  = LoggerFactory.getLogger(DataSynchronizeApplicationTests.class);


    @Test
    public void insertNewClass() {
        DataSynchronization dataSynchronization = new DataSynchronization("sqc", "manual");
        dataSynchronizationMapper.insert(dataSynchronization);

    }

    /**
     * 线程模拟tornado前置客户端收集数据
     * @throws InterruptedException
     */
    @Test
    public void produceData() throws InterruptedException{

        while (true) {
            for (int i = 0; i < 5; i++) {
                MyDataSynThread myDataSynThread = new MyDataSynThread();
                myDataSynThread.start();
            }
            //每20秒写5条数据到本地数据库
//            Thread.sleep();
        }
    }


    @Test
    public void selectData(){
        String a ="1";
        String b = new String("1");
        String c = new String("1");


        Integer a1 =1;
        Integer b1 =new Integer(1);
        Integer c1 =new Integer(1);

        System.out.println(a==b);
        System.out.println(b==c);
        System.out.println(a1==b1);
        System.out.println(b1==c1);

    }


    @Test
    public void goGetSiteCode() throws MalformedURLException {
        String backUrl = "http://diamond.appl.sqc95111.com/biz/config/query?limit=100&offset=0&sort=createTime&order=desc&projectId=316&profileId=931";
        Map<String,String> headers  = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        headers.put("Cookie", "JSESSIONID=9567AF48897213E3CF4A2EB794E89DCF; SPRING_SECURITY_REMEMBER_ME_COOKIE=QkczNzczNTA6MTU1NjY5NDkzOTYzNzo4ZTY2ZDJiYjQzYWZkNjE4MTNmOTk5ZjIzMDQ3ZTlkOA");
        List<Result> results ;
//        Map<String,String> params  = new HashMap<>();
//        params.put("limit", "100");params.put("offset", "0");
//        params.put("sort", "createTime");
//        params.put("order", "desc");
//        params.put("projectId", "316");
//        params.put("profileId", "932");
        //{"pkgCode":"402000005172"}?limit=100&offset=0&sort=createTime&order=desc&projectId=316&profileId=932
        String reponse =  httpSpringRestTemplate.getWithHeader(backUrl,headers);
        reponse = reponse.substring(reponse.indexOf("["),reponse.lastIndexOf("]")+1);
        JsonMapper mapper = JsonMapper.nonDefaultMapper();
        JavaType javaType = mapper.createCollectionType(ArrayList.class, Result.class);
        results = mapper.fromJson(reponse, javaType);
        String index = results.get(0).getProfileName().toString();
        char endChar = index.charAt(index.length()-1);
        if(!CollectionUtils.isEmpty(results)){
            System.out.println(results.get(5).getConfigValue());//990000
            System.out.println(index);//wulumuqi120
            System.out.println(endChar);//0

        }
        else {
            System.out.println("null");
        }


    }

    @Test
    public void goUpdateDiamond() throws IOException {
        String url = "http://diamond.appl.sqc95111.com/biz/config/create";
        Map<String,String> header = new HashMap<>();
        header.put("Cookie", "JSESSIONID=9567AF48897213E3CF4A2EB794E89DCF; SPRING_SECURITY_REMEMBER_ME_COOKIE=QkczNzczNTA6MTU1NjY5NDkzOTYzNzo4ZTY2ZDJiYjQzYWZkNjE4MTNmOTk5ZjIzMDQ3ZTlkOA");
        Map<String,String> params = new HashMap<>();
        params.put("moduleId", "525");
        params.put("configKey", "serverHostCode");
        params.put("configValue", "999911-01");
        params.put("remark", "主机备机区分编码");
        params.put("createInAllProfile", "false");
        params.put("projectId", "316");
        params.put("profileId", "1048");
        String response = httpClientUser.postWithHeader(url, params, header);
        System.out.println(response);

    }

    @Test
    public void amountUpdateDiamond() throws IOException {
        String getUrl = "http://diamond.appl.sqc95111.com/biz/config/query?limit=100&offset=0&sort=createTime&order=desc&projectId=316&profileId=";
        String url = "http://diamond.appl.sqc95111.com/biz/config/create";
        String getObjectUrl;
        char endChar;
        String index;
        String siteCode;
        String result;
        Map<String,String> params = new HashMap<>();
        params.put("moduleId", "525");
        params.put("configKey", "serverHostCode");
        params.put("configValue", "999911-01");
        params.put("remark", "主机备机区分编码");
        params.put("createInAllProfile", "false");
        params.put("projectId", "316");
        params.put("profileId", "1048");
        Map<String,String> headers  = new HashMap<>();
        headers.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        headers.put("Cookie", "JSESSIONID=9567AF48897213E3CF4A2EB794E89DCF; SPRING_SECURITY_REMEMBER_ME_COOKIE=QkczNzczNTA6MTU1NjY5NDkzOTYzNzo4ZTY2ZDJiYjQzYWZkNjE4MTNmOTk5ZjIzMDQ3ZTlkOA");
        for(int i=1111;i<1200;i++){
            getObjectUrl = getUrl+i;
            String reponse =  httpSpringRestTemplate.getWithHeader(getObjectUrl,headers);
            if(reponse.length()<100||reponse==null){
                logger.warn("profileId:{}下 无相关配置",i);
                continue;
            }
            reponse = reponse.substring(reponse.indexOf("["),reponse.lastIndexOf("]")+1);
            JsonMapper mapper = JsonMapper.nonDefaultMapper();
            JavaType javaType = mapper.createCollectionType(ArrayList.class, Result.class);
            List<Result> results ;
            results = mapper.fromJson(reponse, javaType);
            String siteName = results.get(0).getProfileName().toString();
            index = results.get(5).getProfileName().toString();
            endChar = index.charAt(index.length()-1);
            siteCode = results.get(5).getConfigValue().toString();
            System.out.println("profileId:"+i+"   siteName:"+siteName+"  siteCode:"+siteCode+"   endChar:"+endChar);

            if(siteCode!=null&&siteCode.length()==6&&isNumeric(siteCode)){
                params.put("profileId", String.valueOf(i));
                if('0'==endChar){
                    params.put("configValue", (siteCode)+"-01");
                }
                else if('1'==endChar){
                    params.put("configValue", (siteCode)+"-02");
                }
                else{
                    logger.error("siteCode末尾不规范{}",endChar);
                    continue;
                }
                result = httpClientUser.postWithHeader(url, params, headers);
                logger.info("site:{} update serverHostCode success",siteName);
            }
            else{
                logger.info("{}site update serverHostCode fail",siteName);
            }
        }

    }

    public static boolean isNumeric(String str){
        for(int i=str.length();--i>=0;){
            int chr=str.charAt(i);
            if(chr<48 || chr>57)
                return false;
        }
        return true;
    }


    @Test
    public void sqlAddnewColum() throws ClassNotFoundException, SQLException {
//        String urlTerm = "10.53.91.250,10.39.40.250,10.32.59.250,10.51.129.250,10.50.138.250,10.35.40.250," +
//                "10.45.208.250,10.40.48.250,10.32.168.251,10.38.94.250,10.43.138.250,10.42.39.250," +
//                "10.47.120.250,10.40.34.250,10.61.95.250,10.49.158.120,10.48.129.250,10.40.34.251,10.47.120.251," +
//                "10.46.40.250,10.49.34.250,10.36.39.250,10.51.129.251,10.39.40.251,10.34.35.250,10.49.34.251," +
//                "10.33.131.121,10.39.77.121,10.65.217.121, 10.54.78.250, 10.54.134.121, 10.46.40.251, 10.41.32.250," +
//                "10.41.32.251, 10.45.182.251, 10.35.40.251, 10.60.40.121, 10.65.133.250, 10.67.199.120, 10.39.85.251," +
//                "10.65.217.120, 10.54.78.251, 10.52.40.251, 10.49.158.121, 10.40.48.251, 10.32.168.250, 10.36.39.251," +
//                "10.44.44.251, 10.45.208.251, 10.34.35.251, 10.50.138.251, 10.48.166.251, 10.44.44.250, 10.45.182.250," +
//                "10.54.89.250, 10.34.215.251, 10.60.40.120, 10.56.36.251, 10.59.38.120, 10.65.133.251, 10.61.40.251," +
//                "10.61.40.250, 10.42.39.251, 10.67.199.121, 10.48.166.250, 10.37.103.251, 10.44.62.251, " +
//                " 10.65.60.250, 10.44.62.250, 10.44.158.121, 10.65.60.251, 10.39.61.120, 10.67.214.120," +
//                "10.44.158.120, 10.36.40.250, 10.52.40.250, 10.38.94.251, 10.44.94.251,  10.39.61.121," +
//                "10.39.85.250, 10.44.94.250, 10.33.131.120, 10.54.89.251, 10.53.91.251, 10.59.38.121, 10.61.95.251," +//10.61.95.251
//                "10.39.77.120, 10.65.235.251, 10.54.134.120, 10.56.36.250, 10.65.235.250, 10.65.234.251, 10.36.40.251," +
//                "10.67.214.121, 10.48.129.251, 10.65.234.250, 10.37.103.250, 10.32.59.251, 10.34.185.250, 10.34.185.251," +
        String urlTerm = "10.45.72.251, 10.45.62.250, 10.34.215.250, 10.45.72.250, 10.45.62.251,10.49.166.120, 10.49.166.121, 10.64.33.121,10.64.33.120";
        urlTerm = urlTerm.replace(" ", "");
        String[] urlBody = urlTerm.split(",");
        System.out.println(urlTerm);
        for (int i =0; i < 10; i++) {
            if (urlBody[i].charAt(urlBody[i].length()-1) == '1') {
                //sql代码
                sqlMethod(urlBody[i]);
            } else {
                continue;
            }
        }
    }


    public static void sqlMethod(String urlBody) throws ClassNotFoundException, SQLException{
        //声明Connection对象
        Connection con;
        //驱动程序名
        String driver = "com.mysql.jdbc.Driver";
        //URL指向要访问的数据库名mydata
        String urlHead = "jdbc:mysql://";
        String urlTail = ":3306/tornado?useSSL=false";//jdbc:mysql://10.45.9.222:3306/tornado?zeroDateTimeBehavior=convertToNull
        //MySQL配置时的用户名
        String user = "tornado";
        //MySQL配置时的密码
        String password = "tornado123";
        String url = urlHead+urlBody+urlTail;
        try {
            //加载驱动程序
            Class.forName(driver);
            //1.getConnection()方法，连接MySQL数据库！！
            con = DriverManager.getConnection(url, user, password);
            if (!con.isClosed())
                System.out.println("Succeeded connecting to the Database!");
            //2.创建statement类对象，用来执行SQL语句！！
            Statement statement = con.createStatement();
            String sql = "ALTER TABLE `ass_data_synchronization_log`\n" +
                    "ADD  `SERVER_HOST_CODE` VARCHAR(255) NULL COMMENT '主机备机区分编码';";
            statement.execute(sql);
            logger.info("{} add colum successfully ",urlBody);
            con.close();

        }catch (MySQLSyntaxErrorException e){
            logger.error(urlBody+" add colum failed because of the exist");
        }catch (CommunicationsException e){
            //连不上服务器
            logger.info("连接"+urlBody+"失败");
            urlBody = urlBody.substring(0, urlBody.length()-1)+'2';
            url = urlHead+urlBody+urlTail;
            con = DriverManager.getConnection(url, user, password);
            Statement statement = con.createStatement();
            String sql = "ALTER TABLE `ass_data_synchronization_log`\n" +
                    "ADD  `SERVER_HOST_CODE` VARCHAR(255) NULL COMMENT '主机备机区分编码';";
            try {
                logger.info("重连接" + urlBody + "结果:" + statement.execute(sql));
            }catch (MySQLSyntaxErrorException e1){
                logger.error(urlBody+" add colum failed because of the exist");
            }
            con.close();

        }
    }




}

