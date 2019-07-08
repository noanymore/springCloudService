package com.sqc95111.demologin.controller;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class OuterClass{
    static final int a =5;
    static final int b =10;
    static int c = 5;
    static int d =10;



    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue);
        blockingQueue.add("a");
        blockingQueue.add("b");
        blockingQueue.add("c");
        //blockingQueue.add("outOfIndex");
        System.out.println(blockingQueue.offer("outOfIndex2"));
//        blockingQueue.put("outOfIndex3");
        System.out.println(blockingQueue);
        blockingQueue.poll();
        System.out.println(blockingQueue);
        System.out.println(blockingQueue.take());
    }
}