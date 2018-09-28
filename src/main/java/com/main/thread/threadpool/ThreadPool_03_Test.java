package com.main.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author  admin
 * @version 2018/4/23
 * @since 2018/4/23
 */
public class ThreadPool_03_Test {
    //3. newCachedThreadPool
    //创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60秒不执行任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。此线程池不会对线程池大小做限制，
    //线程池大小完全依赖于操作系统（或者说JVM）能够创建的最大线程大小。
    //工作线程的创建数量几乎没有限制(其实也有限制的,数目为Interger. MAX_VALUE), 这样可灵活的往线程池中添加线程。
    //如果长时间没有往线程池中提交任务，即如果工作线程空闲了指定的时间(默认为1分钟)，则该工作线程将自动终止。终止后，如果你又提交了新的任务，则线程池重新创建一个工作线程。
    //在使用CachedThreadPool时，一定要注意控制任务的数量，否则，由于大量线程同时运行，很有会造成系统瘫痪。
    public static void main(String[] args) {
        ExecutorService cachedPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            cachedPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("CurrentThreadName="+Thread.currentThread().getName()+"，index的值="+index);
                        Thread.sleep(1200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        cachedPool.shutdown();
    }

}
