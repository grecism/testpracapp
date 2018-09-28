package com.main.thread.threadpool;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author  admin
 * @version 2018/4/24
 * @since 2018/4/24
 */
public class ThreadPool_06_Test01 {
    //FutureTask获取线程返回值, 用来获取耗时较长的计算结果。
    //模拟一个会计算账的过程，主线程已经获得A帐户的总额了，为了不让主线程等待CallableImpl类的计算结果的返回而启用新的线程去处理，并使用 FutureTask对象来监控，这样，主线程还可以继续做其他事情，
    //最后需要计算总额的时候再尝试去获得CallableImpl的信息。
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //初始化一个Callable对象和FutureTask对象
        CallableImpl callableImpl = new CallableImpl();
        FutureTask futureTask = new FutureTask(callableImpl);
        //使用futureTask创建一个线程
        Thread thread = new Thread(futureTask);
        System.out.println("futureTask线程现在开始启动，启动时间为：" + System.nanoTime());
        thread.start();
        System.out.println("主线程开始执行其他任务");
        //从A账户获取总金额
        int aAcountMoney = new Random().nextInt(10000);
        System.out.println("现在A账户中的总金额为" + aAcountMoney);
        System.out.println("等待B账户总金额统计完毕...");
        //测试后台的计算线程是否完成，如果未完成则等待
        while (!futureTask.isDone()){
            try {
                Thread.sleep(500);
                System.out.println("B账户计算未完成继续等待...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("futureTask线程计算完毕，此时时间为" + System.nanoTime());
        try {
            Integer getBAccountMoney = (Integer) futureTask.get();
            Integer totalMoney = aAcountMoney+getBAccountMoney;
            System.out.println("您现在的总金额totalMoney=A+B为：" + totalMoney);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

class CallableImpl implements Callable{
    Integer bAccountMoney;

    @Override
    public Object call() throws Exception {
        Thread.sleep(5000);
        bAccountMoney = new Integer(new Random().nextInt(10000));
        System.out.println("B账户的总额为："+bAccountMoney);
        return bAccountMoney;
    }
}