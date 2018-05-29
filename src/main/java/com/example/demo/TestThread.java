package com.example.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author JinXing
 * @date 2018/5/25 18:18
 */
public class TestThread implements Runnable{

    private List list;
    CountDownLatch cdl;


    public TestThread(List list,CountDownLatch cdl){
        this.list=list;
        this.cdl=cdl;
    }
    /**
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        for(int i=0;i<500;i++){
            list.add("a");
        }
        try {
            cdl.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        int count=32;
        CountDownLatch cdl=new CountDownLatch(count);
//        List list=new ArrayList();
        List list= Collections.synchronizedList(new ArrayList());
        TestThread t1=new TestThread(list,cdl);
        for(int i=0;i<count;i++){
            new Thread(t1).start();
        }
        cdl.await();
        System.out.println("size:"+list.size());
    }

}
