package com.main.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lulu.zou
 * @version 2018/12/19
 * @since 2018/12/19
 */
public class ThreadPool_02_Test01 {
    //newFixedThreadPool创建一个可重用固定线程数的线程池，以共享的无界队列方式来运行这些线程。
    //运行结果：总共只会创建5个线程， 开始执行五个线程，当五个线程都处于活动状态，再次提交的任务都会加入队列等到其他线程运行结束，当线程处于空闲状态时会被下一个任务复用。
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i = 0; i < 20 ;i++){
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    System.out.println("CurrentThreadName="+Thread.currentThread().getName());
                }
            };
            executorService.execute(runnable);
        }
        executorService.shutdown();
    }
}
