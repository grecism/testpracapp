package com.main.thread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author lulu.zou
 * @version 2018/4/23
 * @since 2018/4/23
 */
public class ThreadPool_04_Test {
    //4.newScheduledThreadPool
    //创建一个定长的线程池，而且支持定时的以及周期性的任务执行，支持定时及周期性任务执行。
    //-1延迟3秒执行，延迟执行示例代码如下。
    //-1延迟3秒执行。
    //-2表示延迟1秒后每3秒执行一次，定期执行示例代码如下。
    //-2表示延迟1秒后每3秒执行一次。
    public static void main(String[] args) {
        ScheduledExecutorService scheduledePool = Executors.newScheduledThreadPool(5);
        //-1
       /* scheduledePool.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 3 seconds CurrentTime="+System.currentTimeMillis());
            }
        },3,TimeUnit.SECONDS);
        scheduledePool.shutdown();*/

        scheduledePool.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("delay 1 seconds, and excute every 3 seconds");
            }
        },1,3,TimeUnit.SECONDS);
    }
}
