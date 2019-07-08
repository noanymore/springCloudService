package com.sqc95111.datasynchronize.service;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/4/4 14:13
 * @Version 1.0
 */
@Service
public class SqlServiceImpl implements SqlService {

    private static Logger logger  = LoggerFactory.getLogger(SqlServiceImpl.class);


    @Async
    @Override
    public void sqlMethod(String urlBody) throws ClassNotFoundException, SQLException {
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
            logger.info(String.valueOf(statement.execute(sql)));
            con.close();
            logger.info(urlBody+"add colum successfully");

        }catch (MySQLSyntaxErrorException e){
            e.printStackTrace();
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
            logger.info("重连接"+urlBody+"结果:"+statement.execute(sql));
            con.close();

        }
    }
}
