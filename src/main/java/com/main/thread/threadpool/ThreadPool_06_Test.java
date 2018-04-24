package com.main.thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author lulu.zou
 * @version 2018/4/23
 * @since 2018/4/23
 */
public class ThreadPool_06_Test {
    //Executor执行Callable任务
    //在Java 5之后，任务分两类：一类是实现了Runnable接口的类，一类是实现了Callable接口的类。两者都可以被ExecutorService执行，但是Runnable任务没有返回值，而Callable任务有返回值。
    //并且Callable的call()方法只能通过ExecutorService的submit(Callable<T> task) 方法来执行，并且返回一个 <T>Future<T>，是表示任务等待完成的 Future。Callable接口类似于Runnable，
    //两者都是为那些其实例可能被另一个线程执行的类设计的。但是 Runnable 不会返回结果，并且无法抛出经过检查的异常而Callable又返回结果，而且当获取返回结果时可能会抛出异常。
    //Callable中的call()方法类似Runnable的run()方法，区别同样是有返回值，后者没有。当将一个Callable的对象传递给ExecutorService的submit方法，则该call方法自动在一个线程上执行，
    //并且会返回执行结果Future对象。同样，将Runnable的对象传递给ExecutorService的submit方法，则该run方法自动在一个线程上执行，并且会返回执行结果Future对象，但是在该Future对象上调用get方法，将返回null。
    public static void main(String[] args) {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        List<Future<String>> resultList = new ArrayList<Future<String>>();
        //创建10个任务并执行。
        for (int i = 0; i < 10; i++) {
            //使用cachedThreadPool执行Callable类型的任务，并将结果保存在future变量中。
            Future<String> future = cachedThreadPool.submit(new ThreadPool_06_Thread(i));
            //将任务执行结果存储到List中。
            resultList.add(future);
        }

        for (Future<String> fs: resultList) {
            try {
                //Future返回如果没有完成，则一直循环等待，直到Future返回完成。
                while(!fs.isDone());
                //打印各个线程（任务）执行的结果
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }finally {
                //启动一次顺序关闭，执行以前提交的任务，但不接受新任务。
                cachedThreadPool.shutdown();
            }
        }
    }
}

class ThreadPool_06_Thread implements Callable<String>{
    private int index;

    public ThreadPool_06_Thread(int index) {
        this.index = index;
    }
    /**
     * 任务的具体过程，一旦任务传给ExecutorService的submit方法，
     * 则该方法自动在一个线程上执行。
     */
    @Override
    public String call() throws Exception {
        System.out.println("CurrentThreadName="+Thread.currentThread().getName()+"call()方法被自动调用。");
        //该返回结果将被Future的get方法得到。
        String result = "call()方法被自动调用，任务返回的结果是："+Thread.currentThread().getName()+"_"+index;
        return result;
    }
}














