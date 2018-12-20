package com.main.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author admin
 * @version 2018/12/19
 * @since 2018/12/19
 */
public class ThreadPool_01_Test01 {
    //newSingleThreadExecutor创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
    //运行结果：只会创建一个线程，当上一个执行完之后才会执行第二个。
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for(int i = 0 ; i < 20 ; i++){
            Runnable runnable = new Runnable(){
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
