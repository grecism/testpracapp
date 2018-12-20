package com.main.thread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * @version 2018/12/19
 * @since 2018/12/19
 */
public class ThreadPool_04_Test03 {
    //scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit)。
    //command：执行线程 initialDelay：初始化延时 period：前一次执行结束到下一次执行开始的间隔时间（间隔执行延迟时间） unit：计时单位
    //创建并执行一个在给定初始延迟后首次启用的定期操作，随后，在每一次执行终止和下一次执行开始之间都存在给定的延迟。
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("CurrentThreadName="+Thread.currentThread().getName());
            }
        };
        executorService.scheduleWithFixedDelay(runnable,5,1, TimeUnit.SECONDS);
    }
}
