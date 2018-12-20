package com.main.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author admin
 * @version 2018/12/19
 * @since 2018/12/19
 */
public class ThreadPool_03_Test01 {
    //newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程。
    //运行结果：可以看出缓存线程池大小是不定值，可以需要创建不同数量的线程，在使用缓存型池时，先查看池中有没有以前创建的线程，如果有，就复用.如果没有，就新建新的线程加入池中，缓存型池子通常用于执行一些生存期很短的异步型任务。
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 100 ; i++){
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
