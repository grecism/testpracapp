package com.main.thread.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author  admin
 * @version 2018/4/23
 * @since 2018/4/23
 */
public class ThreadPool_01_Test {
    //线程的使用在java中占有极其重要的地位，在jdk1.4极其之前的jdk版本中，关于线程池的使用是极其简陋的。在jdk1.5之后这一情况有了很大的改观。Jdk1.5之后加入了java.util.concurrent包，
    //这个包中主要介绍java中线程以及线程池的使用。为我们在开发中处理线程的问题提供了非常大的帮助。
    /**blog**/
    //https://blog.csdn.net/he90227/article/details/52576452
    //http://www.silencedut.com/2016/06/15/Callable%E5%92%8CFuture%E3%80%81FutureTask%E7%9A%84%E4%BD%BF%E7%94%A8/
    /**线程池框架Executor**/
    //java中的线程池是通过Executor框架实现的，Executor 框架包括类：Executor，Executors，ExecutorService，ThreadPoolExecutor ，Callable和Future、FutureTask的使用等。
    //corePoolSize：线程池的核心线程数,线程池中运行的线程数也永远不会超过 corePoolSize 个,默认情况下可以一直存活。可以通过设置allowCoreThreadTimeOut为True,此时核心线程数就是0,
    //此时keepAliveTime控制所有线程的超时时间。
    //maximumPoolSize：线程池允许的最大线程数。
    //keepAliveTime： 指的是空闲线程结束的超时时间。
    //unit：是一个枚举，表示 keepAliveTime 的单位。
    //workQueue：表示存放任务的BlockingQueue<Runnable队列。
    //BlockingQueue：阻塞队列（BlockingQueue）是java.util.concurrent下的主要用来控制线程同步的工具。如果BlockQueue是空的,从BlockingQueue取东西的操作将会被阻断进入等待状态,直到BlockingQueue进了东西才会被唤醒。
    //同样,如果BlockingQueue是满的,任何试图往里存东西的操作也会被阻断进入等待状态,直到BlockingQueue里有空间才会被唤醒继续操作。阻塞队列常用于生产者和消费者的场景，生产者是往队列里添加元素的线程，
    //消费者是从队列里拿元素的线程。阻塞队列就是生产者存放元素的容器，而消费者也只从容器里拿元素。具体的实现类有LinkedBlockingQueue,ArrayBlockingQueued等。一般其内部的都是通过Lock和Condition(显示锁（Lock）及Condition的学习与使用)来实现阻塞和唤醒。
    /**线程池的工作过程如下**/
    //1.线程池刚创建时，里面没有一个线程。任务队列是作为参数传进来的。不过，就算队列里面有任务，线程池也不会马上执行它们。
    //2.当调用 execute() 方法添加一个任务时，线程池会做如下判断：
    //  （1）如果正在运行的线程数量小于 corePoolSize，那么马上创建线程运行这个任务；
    //  （2）如果正在运行的线程数量大于或等于 corePoolSize，那么将这个任务放入队列；
    //  （3）如果这时候队列满了，而且正在运行的线程数量小于 maximumPoolSize，那么还是要创建非核心线程立刻运行这个任务；
    //  （4）如果队列满了，而且正在运行的线程数量大于或等于 maximumPoolSize，那么线程池会抛出异常RejectExecutionException。
    //3.当一个线程完成任务时，它会从队列中取下一个任务来执行。
    //4.当一个线程无事可做，超过一定的时间（keepAliveTime）时，线程池会判断，如果当前运行的线程数大于 corePoolSize，那么这个线程就被停掉。所以线程池的所有任务完成后，它最终会收缩到 corePoolSize 的大小。
    /**线程池工作原理**/
    //线程池作用就是限制系统中执行线程的数量。根据系统的环境情况，可以自动或手动设置线程数量，达到运行的最佳效果；少了浪费了系统资源，多了造成系统拥挤效率不高。用线程池控制线程数量，其他线程排队等候。
    //一个任务执行完毕，再从队列的中取最前面的任务开始执行。若队列中没有等待进程，线程池的这一资源处于等待。当一个新任务需要运行时，如果线程池中有等待的工作线程，就可以开始运行了；否则进入等待队列。
    /**线程池的作用**/
    //1.减少了创建和销毁线程的次数，每个工作线程都可以被重复利用，可执行多个任务。
    //2.可以根据系统的承受能力，调整线程池中工作线线程的数目，防止因为消耗过多的内存，而把服务器累趴下(每个线程需要大约1MB内存，线程开的越多，消耗的内存也就越大，最后死机)。
    /**任务缓存队列及排队策略**/
    //任务缓存队列，即workQueue，它用来存放等待执行的任务。
    //workQueue的类型为BlockingQueue<Runnable>，通常可以取下面三种类型：
    // （1）ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；
    // （2）LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；
    // （3）synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。
    /**任务拒绝策略**/
    //当线程池的任务缓存队列已满并且线程池中的线程数目达到maximumPoolSize，如果还有任务到来就会采取任务拒绝策略，通常有以下四种策略：
    //ThreadPoolExecutor.AbortPolicy:丢弃任务并抛出RejectedExecutionException异常。
    //ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
    //ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
    //ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
    //Java里面线程池的顶级接口是Executor，但是严格意义上讲Executor并不是一个线程池，而只是一个执行线程的工具。真正的线程池接口是ExecutorService。
    //要配置一个线程池是比较复杂的，尤其是对于线程池的原理不是很清楚的情况下，很有可能配置的线程池不是较优的，因此在Executors类里面提供了一些静态工厂，生成一些常用的线程池。
    //（1） newSingleThreadExecutor（2）newFixedThreadPool（3）newCachedThreadPool（4）newScheduledThreadPool
    //1. newSingleThreadExecutor
    // 创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。如果这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
    public static void main(String[] args) {
        ExecutorService singlePool = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            final int index = i;
            singlePool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("CurrentThreadName="+Thread.currentThread().getName()+"，index的值="+index);
                        Thread.sleep(1300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        singlePool.shutdown();
    }
}
