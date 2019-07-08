package com.sqc95111.datasynchronize.service;

import com.sqc95111.datasynchronize.thread.MyDataSynThread;
import org.springframework.stereotype.Service;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/1/31 14:40
 * @Version 1.0
 */
@Service
public class DataSynchronizationServiceImpl implements DataSynchronizationService {


    public int DATA_INTERVAL = 10000;

/**
 * 默认同步数据范围为一小时之前的全部数据
 */
//    @Scheduled(cron = "0/10 * * * * ? ")
//    @Scheduled(cron = "0/10 * * * * ? ")
    @Override
    public void DataSynchronizationSchedule() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            MyDataSynThread myDataSynThread = new MyDataSynThread();
            myDataSynThread.start();
        }
        //每20秒写5条数据到本地数据库
        Thread.sleep(DATA_INTERVAL);

    }
}
