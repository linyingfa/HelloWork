package thread.executor;

import java.util.concurrent.*;

public class MyExecutor {

//   Executor框架的结构 : Extecutor是一个接口，它是Executor框架的基础，它将任务的提交与任务的执行分离开来。
//        主要包括3个部分:
//        TODO 1.【任务】：
//                              包括被执行任务需要实现的接口：Runnable接口或Callable接口
//                              Runnable接口和Callable接口的实现类，都可以被ThreadPoolExecutor或者ScheduledThreadPoolExecutor执行。
//                              区别就是Runnable无法返回执行结果，而Callable可以返回执行结果。

//        TODO 2.【任务的执行】：
//                              包括任务执行机制的核心接口Executor，以及继承自Executor的EexcutorService接口。
//                              Exrcutor有两个关键类实现了ExecutorService接口（ThreadPoolExecutor和ScheduledThreadPoolExecutor）。
//                              TODO  ThreadPoolExecutor是线程池的核心实现类，用来执行被提交的任务。
//                              TODO  ScheduledThreadPoolExecutor是一个实现类，可以在给定的延迟后运行命令，或者定期执行命令。
//                              ScheduledThreadPoolExecutor比Timer更灵活，功能更强大。

//        TODO 3.【异步计算的结果】：
//                               包括接口Future和实现Future接口的FutureTask类
//                               Future接口和实现Future接口的FutureTask类，代表异步计算的结果。


//        分析说明：
//       TODO 主线程首先创建实现Runnable或Callable接口的任务对象，
//        工具类Executors可以把一个Runnable对象封装为一个Callable对象,使用如下两种方式：
//        1.Executors.callable(Runnable task)
//        2.Executors.callable(Runnable task,Object resule)。

//        TODO 然后可以把Runnable对象直接提交给ExecutorService执行，
//        方法为ExecutorService.execute(Runnable command)；
//        或者也可以把Runnable对象或者Callable对象提交给ExecutorService执行，
//        方法为ExecutorService.submit(Runnable task)或ExecutorService.submit(Callable<T> task)。
//        这里需要注意的是如果执行ExecutorService.submit(...),
//        ExecutorService将返回一个实现Future接口的对象（其实就是FutureTask）。当然由于FutureTask实现了Runnable接口，
//        我们也可以直接创建FutureTask，然后提交给ExecutorService执行。到此Executor框架的主要体系结构。


//       TODO ThreadPoolExecutor浅析
//       ThreadPoolExecutor是线程的真正实现，通常使用工厂类Executors来创建，
//       但它的构造方法提供了一系列参数来配置线程池，下面我们就先介绍ThreadPoolExecutor的构造方法中各个参数的含义。

/*    public  ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, defaultHandler);}*/


    //TODO corePoolSize：                    1
//                       线程池的核心线程数，默认情况下，核心线程数会一直在线程池中存活，即使它们处理闲置状态。
//                       如果将ThreadPoolExecutor的allowCoreThreadTimeOut属性设置为true，
//                       那么闲置的核心线程在等待新任务到来时会执行超时策略，
//                       这个时间间隔由keepAliveTime所指定，
//                       当等待时间超过keepAliveTime所指定的时长后，核心线程就会被终止。

//   TODO  maximumPoolSize：                 2
//                        线程池所能容纳的最大线程数量，当活动线程数到达这个数值后，后续的新任务将会被阻塞。

//   TODO keepAliveTime：                    3
//                      非核心线程闲置时的超时时长，超过这个时长，非核心线程就会被回收。
//                      当ThreadPoolExecutor的allowCoreThreadTimeOut属性设置为true时，keepAliveTime同样会作用于核心线程。

//   TODO unit：                             4
//                      用于指定keepAliveTime参数的时间单位，
//                      这是一个枚举，常用的有TimeUnit.MILLISECONDS(毫秒)，
//                      TimeUnit.SECONDS(秒)以及TimeUnit.MINUTES(分钟)等。

//  TODO  workQueue：                        5
//                      线程池中的任务队列，通过线程池的execute方法提交Runnable对象会存储在这个队列中。


//  TODO  threadFactory：                    6
//                       线程工厂，为线程池提供创建新线程的功能。ThreadFactory是一个接口，它只有一个方法：Thread newThread（Runnable r）。


    //    除了上面的参数外还有个不常用的参数，RejectExecutionHandler(The default rejected execution handler)
//    这个参数表示当ThreadPoolExecutor已经关闭或者ThreadPoolExecutor已经饱和时（//TODO 达到了最大线程池大小而且工作队列已经满），
//    execute方法将会调用Handler的rejectExecution方法来通知调用者，默认情况 下是抛出一个RejectExecutionException异常。
//  TODO  ThreadPoolExecutor执行任务时的大致规则：
//            （1）如果线程池的数量还未达到核心线程的数量，那么会直接启动一个核心线程来执行任务
//            （2）如果线程池中的线程数量已经达到或者超出核心线程的数量，那么任务会被插入到任务队列中排队等待执行。
//            （3）如果在步骤（2）中无法将任务插入到任务队列中，这往往是由于任务队列已满，
//                 这个时候如果线程数量未达到线程池规定的最大值，那么会立刻启动一个非核心线程来执行任务。
//            （4）如果在步骤（3）中线程数量已经达到线程池规定的最大值，那么就会拒绝执行此任务
//                 ThreadPoolExecutor会调用RejectExecutionHandler的rejectExecution方法来通知调用者。
//   3种常见的线程池，它们都直接或者间接地通过配置ThreadPoolExecutor来实现自己的功能特性，
    //TODO  ThreadPoolExecutor extends  AbstractExecutorService implements ExecutorService extends Executor
//    TODO 这个4种线程池分别是(其实这几种只是一个创建线程池的方法名字而已，最终还是通过ThreadPoolExecutor)

    //      TODO    FixedThreadPool：
//                           FixedThreadPool模式会使用一个优先固定数目的线程来处理若干数目的任务。
//                           规定数目的线程处理所有任务，一旦有线程处理完了任务就会被用来处理新的任务(如果有的话)。
//                           FixedThreadPool模式下最多的线程数目是一定的(//TODO 核心数和最大数一致)
//                           ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
//                           public static ExecutorService newFixedThreadPool(int nThreads) {
//                                return new ThreadPoolExecutor(nThreads, nThreads,
//                                        0L, TimeUnit.MILLISECONDS,
//                                        new LinkedBlockingQueue<Runnable>());//todo LinkedBlockingQueue  没界限的
//                            }


//       TODO    CachedThreadPool：
//                              CachedThreadPool首先会按照需要创建足够多的线程来执行任务(Task)。
//                              随着程序执行的过程，有的线程执行完了任务，可以被重新循环使用时，
//                              才不再创建新的线程来执行任务。
//                              ExecutorService cachedThreadPool=Executors.newCachedThreadPool();
//                              public static ExecutorService newCachedThreadPool() {
//                                    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
//                                            60L, TimeUnit.SECONDS,
//                                            new SynchronousQueue<Runnable>());//todo SynchronousQueue 没有容量
//                                }

//                                从该静态方法，我们可以看到CachedThreadPool的corePoolSize被设置为0，
//                                 而maximumPoolSize被设置Integer.MAX_VALUE，即maximumPoolSize是无界的，
//                                 而keepAliveTime被设置为60L，单位为妙。也就是空闲线程等待时间最长为60秒，超过该时间将会被终止。
//                                 而且在这里CachedThreadPool使用的是没有容量的SynchronousQueue作为线程池的工作队列，
//                                 但其maximumPoolSize是无界的，也就是意味着如果
//                                 主线程提交任务的速度高于maximumPoolSize中线程处理任务的速度时
                                // CachedThreadPool将会不断的创建新的线程，
                                // 在极端情况下，CachedThreadPool会因为创建过多线程而耗尽CPU和内存资源。

//              TODO 分析：
//            （1）首先执行SynchronousQueue.offer(Runnable task)，添加一个任务。
                    // 如果当前CachedThreadPool中有空闲线程正在执行SynchronousQueue.poll(keepAliveTime,TimeUnit.NANOSECONDS),
                    // 其中NANOSECONDS是毫微秒即十亿分之一秒（就是微秒/1000），
                    // 那么主线程执行offer操作与空闲线程执行poll操作配对成功，
                    // 主线程把任务交给空闲线程执行，execute()方法执行完成，否则进入第（2）步。

//            （2）当CachedThreadPool初始线程数为空时，或者当前没有空闲线程，
                    // 将没有线程去执行SynchronousQueue.poll(keepAliveTime,TimeUnit.NANOSECONDS)。
                    // 这样的情况下，步骤（1）将会失败，此时CachedThreadPool会创建一个新的线程来执行任务，
                    // execute()方法执行完成。

//            （3）在步骤（2）中创建的新线程将任务执行完成后，会执行SynchronousQueue.poll(keepAliveTime,TimeUnit.NANOSECONDS)，
//                 这个poll操作会让空闲线程最多在SynchronousQueue中等待60秒，如果60秒内主线程提交了一个新任务，
//                 那么这个空闲线程将会执行主线程提交的新任务，否则，这个空闲线程将被终止。
//                 由于空闲60秒的空闲线程会被终止，因此长时间保持空闲的 CachedThreadPool是不会使用任何资源的。

//        TODO              ScheduledThreadPool


//       TODO               SingleThreadExecutor。

}
