package com.main.thread.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author admin
 * @version 2018/12/19
 * @since 2018/12/19
 */
public class ThreadPool_04_Test02 {
    //scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnitunit)。
    //command：执行线程 initialDelay：初始化延时 period：两次开始执行最小间隔时间 unit：计时单位
    //建并执行一个在给定初始延迟后首次启用的定期操作，后续操作具有给定的周期；也就是将在 initialDelay 后开始执行，然后在initialDelay+period 后执行，接着在 initialDelay + 2 * period 后执行，依此类推。
    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("CurrentThreadName=" + Thread.currentThread().getName());
            }
        };
        executorService.scheduleAtFixedRate(runnable,5000,3000, TimeUnit.MILLISECONDS);
    }
}
