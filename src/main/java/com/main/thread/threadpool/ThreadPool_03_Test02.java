package com.main.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author admin
 * @version 2018/12/19
 * @since 2018/12/19
 */
public class ThreadPool_03_Test02 {
    //一旦Runnable任务传递到execute（）方法，该方法便会自动在一个线程上执行。
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0 ; i < 5 ; i++){
            executorService.execute(new TestRunnable());
            System.out.println("============执行"+i+"==============");
        }
        executorService.shutdown();
    }
}

class TestRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("CurrentThreadName="+Thread.currentThread().getName()+"线程被调用了。");
    }
}

