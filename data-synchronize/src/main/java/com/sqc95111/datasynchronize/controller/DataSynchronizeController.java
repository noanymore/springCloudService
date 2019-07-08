package com.sqc95111.datasynchronize.controller;

import com.sqc95111.datasynchronize.constant.ResponseResult;
import com.sqc95111.datasynchronize.service.DataSynchronizationService;
import com.sqc95111.datasynchronize.util.DateUtil;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/1/31 14:39
 * @Version 1.0
 */
@RestController
public class DataSynchronizeController {

    @Autowired
    DataSynchronizationService dataSynchronizationService;

    @PostMapping("/DATA")
    public ResponseResult selectData(){
        Date startTime = DateUtils.addMinutes(DateUtil.truncateTo10Min(new Date()), -15);
        Date endTime = DateUtils.addMinutes(DateUtil.truncateTo10Min(new Date()), -5);

        return ResponseResult.build();
    }

}
