package com.sqc95111.datasynchronize.model;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/1/30 17:20
 * @Version 1.0
 */
@Data
public class DataSynchronization {

    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private static final long serialVersionUID = 1L;

    private int id;

    private String uuid;

    private String operator;

    private String createTime;

    private String type = "auto";

    public static void main(String[] args) {
        DataSynchronization dataSynchronization = new DataSynchronization("1","1");
        System.out.println(dataSynchronization);
    }

    public DataSynchronization(String operator, String type) {
        this.uuid = UUID.randomUUID().toString().replace("-", "");
        this.operator = operator;
        this.createTime = SIMPLE_DATE_FORMAT.format(new Date());
        if(type!=null) {
            this.type = type;
        }
    }
}
