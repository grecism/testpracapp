package com.main.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lulu.zou
 * @version 2018/4/23
 * @since 2018/4/23
 */
public class ThreadPool_02_Test {
    //2.newFixedThreadPool
    //创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。线程池的大小一旦达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
    //如果工作线程数量达到线程池初始的最大数，则将提交的任务存入到池队列中。FixedThreadPool是一个典型且优秀的线程池，它具有线程池提高程序效率和节省创建线程时所耗的开销的优点。但是，在线程池空闲时，
    //即线程池中没有可运行任务时，它不会释放工作线程，还会占用一定的系统资源。
    public static void main(String[] args) {
        ExecutorService fixedPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("CurrentThreadName="+Thread.currentThread().getName()+"，index的值="+index);
                        Thread.sleep(1100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        fixedPool.shutdown();
    }
}
