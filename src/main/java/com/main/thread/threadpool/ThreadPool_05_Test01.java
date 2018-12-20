package com.main.thread.threadpool;

import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author  admin
 * @version 2018/12/19
 * @since 2018/12/19
 */
public class ThreadPool_05_Test01 {
    public static void main(String[] args) {
        LinkedBlockingDeque<Runnable> arrayBlockingQueue = new LinkedBlockingDeque<Runnable>();
        ThreadPoolExecutor executorService = new ThreadPoolExecutor(20,25,60, TimeUnit.SECONDS,arrayBlockingQueue);
        //ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 0 ; i < 10000 ; i++){
            final int taskId = i;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        //System.out.println("StartTime==========================="+System.currentTimeMillis()+"==================开始任务======"+taskId);
                        //System.out.println("CurrentThreadName="+Thread.currentThread().getName()+"==================执行任务======"+ taskId);
                        //Thread.sleep(5000);
                        //System.out.println("EndTime============================"+System.currentTimeMillis()+"==================结束任务======"+ taskId);
                        if(taskId % 3 == 0){
                            System.out.println("CurrentThreadName="+Thread.currentThread().getName()+"==================执行任务异常======"+ taskId);
                        }else{
                            System.out.println("CurrentThreadName="+Thread.currentThread().getName()+"==================执行任务======"+ taskId);
                        }
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }
}
