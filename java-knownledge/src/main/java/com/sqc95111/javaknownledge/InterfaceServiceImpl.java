package com.sqc95111.javaknownledge;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/4/8 10:51
 * @Version 1.0
 */

@Service
public class InterfaceServiceImpl implements  InterfaceService{

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void method(){
        logger.info("method 执行");
        return ;
    }
}
