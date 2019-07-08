package com.sqc95111.datasynchronize.thread;


import com.sqc95111.datasynchronize.mapper.DataSynchronizationMapper;
import com.sqc95111.datasynchronize.model.DataSynchronization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Random;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/1/30 17:18
 * @Version 1.0
 */
public class MyDataSynThread extends Thread implements Runnable{

    @Autowired
    DataSynchronizationMapper dataSynchronizationMapper = (DataSynchronizationMapper) SpringContextHolder.getBean("dataSynchronizationMapper");

    public static Random random = new Random();
    public static Logger log = LoggerFactory.getLogger((MyDataSynThread.class));

    static int[] randomNum = new int[7];
    static char[] operator = new char[7];


    @Override
    public void run() {
        for(int i=0;i<randomNum.length;i++){
            randomNum[i] = Math.abs(random.nextInt()%(122-65))+65;
            while(randomNum[i]>90&&randomNum[i]<97){
                randomNum[i] = Math.abs( random.nextInt()%(122-65))+65;
            }
            operator[i] = (char)randomNum[i];
        }
        String operatorName = String.copyValueOf(operator);
        DataSynchronization dataSynchronization = new DataSynchronization(operatorName, null);
        dataSynchronizationMapper.insert(dataSynchronization);
        log.info("插入本地同步数据表  当前操作时间: {},操作人: {},操作类型: {}",
                dataSynchronization.getCreateTime(),dataSynchronization.getOperator(),dataSynchronization.getType());
    }


//    public static void main(String[] args) {
//        int[] randomNum = new int[7];
//        char[] operator = new char[7];
//
//
//          for(int i=0;i<randomNum.length;i++){
//            randomNum[i] = Math.abs(random.nextInt()%(122-65))+65;
//            System.out.println("随机数:"+randomNum[i]);
//            randomNum[i]=91;
//            while(randomNum[i]>90&&randomNum[i]<97){
//                randomNum[i] = Math.abs( random.nextInt()%(122-65))+65;
//            }
//            operator[i] = (char)randomNum[i];
//            System.out.println("rand:"+randomNum[i]+" operator:"+operator[i]);
//        }
//        String operatorName = String.copyValueOf(operator);
//        System.out.println(operatorName);


}



