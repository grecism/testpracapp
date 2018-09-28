package com.main.thread.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author  admin
 * @version 2018/4/23
 * @since 2018/4/23
 */
public class ThreadPool_05_Test {
    //自定义线程池，可以用ThreadPoolExecutor类创建，它有多个构造方法来创建线程池，用该类很容易实现自定义的线程池。
    public static void main(String[] args) {
        //创建等待队列。
        ArrayBlockingQueue<Runnable> arrayBlockingQueue = new ArrayBlockingQueue<Runnable>(20);
        //创建线程池，池中保存的线程数为3，允许的最大线程数为5。
        ThreadPoolExecutor pool = new ThreadPoolExecutor(3,5,50, TimeUnit.SECONDS,arrayBlockingQueue);
        //创建10个任务。
        for (int i = 0; i < 20; i++) {
            pool.execute(new ThreadPool_05_Thread(i));
            System.out.println("线程池中线程数目："+pool.getPoolSize()+"，队列中等待执行的任务数目："+
                    pool.getQueue().size()+"，已执行完别的任务数目："+pool.getCompletedTaskCount());
        }
        pool.shutdown();
    }
}

class ThreadPool_05_Thread implements Runnable{
    private int index;
    public ThreadPool_05_Thread(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        try {
            System.out.println("CurrentThreadName="+Thread.currentThread().getName()+"正在执行。。。"+index);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}