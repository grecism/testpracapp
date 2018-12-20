package com.main.thread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * @version 2018/12/19
 * @since 2018/12/19
 */
public class ThreadPool_04_Test01 {
    //newScheduledThreadPool创建一个定长线程池，支持定时及周期性任务执行。schedule(Runnable command,long delay, TimeUnit unit)创建并执行在给定延迟后启用的一次性操作。
    //表示从提交任务开始计时，5000毫秒后执行。
    //运行结果和newFixedThreadPool类似，不同的是newScheduledThreadPool是延时一定时间之后才执行。
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        for(int i = 0; i < 20; i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("CurrentThreadName=" + Thread.currentThread().getName());
                }
            };
            executorService.schedule(runnable,5000, TimeUnit.MILLISECONDS);
        }
        executorService.shutdown();
    }
}
