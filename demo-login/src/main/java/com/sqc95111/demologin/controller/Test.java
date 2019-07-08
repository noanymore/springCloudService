package com.sqc95111.demologin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

/**
 * @ClassName ${Name}
 * @Description TODO
 * @Author < a href="jcsong50@best-inc.com">sqc</a>
 * @Date 2019/1/28 14:27
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {
        Logger log = LoggerFactory.getLogger(Test.class);
        String token = "1";
        String mode = "2";
        log.info("请求任务的token：{} 分拣模式：{}", token, mode);
        log.info("");


        Set<String> s = new TreeSet<String>();
        Collections.addAll(s,args);
        System.out.println(s);
    }



}
