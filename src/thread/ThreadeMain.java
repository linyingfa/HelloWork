package thread;

import java.util.concurrent.*;

public class ThreadeMain {

    public static void main(String[] args) {
//        Thread和Runnable的关系();
//        Runnable和Callable的区别();
//        futures();
        FutureTask();
    }

    //TODO Thread和Runnable的关系
    private static void Thread和Runnable的关系() {
        //todo Thread就是实现Runnable的，所以Thread也可以说是Runnable
        //todo  private Runnable target;   Thread里面的一个变量 实例一个线程传入的runable就是这个target
        //todo 当target不为null的时候，线程start方法里面调用nativeCreate方法，然后再调用run方法，如果target不为空调用target的run方法
        //todo 如果target为null，则调用自身的run方法

        //TODO 源码如下
//        @Override public void run () {
//            if (target != null) {
//                target.run();
//            }
//        }


        new Thread(new Runnable() {
            @Override
            public void run() {
                // 子线程操作
                System.out.println("1111111111");
            }
        }).start();

        MyThread myThread = new MyThread();
        myThread.start();
//        myThread.run();//todo  千万不要通过对象直接调用run方法，否则是对象调用普通的run方法而已，还是在主线程的

    }


    private static void Runnable和Callable的区别() {
        //TODO Runnable和Callable的区别
        //TODO 可以发现，Callable带有一个泛型的返回值，而Runnable并没有返回值，
        //TODO  所以使用Callable可以返回线程的结果，而Runnable不行
    }

    //TODO Future,Future不是线程，它可以理解为管理线程的人，不过它对线程的管理方法不是很多，
    //todo  这点可以从Future的源码中看出来
    private static void futures() {
        //线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);

        //第一步：创建线程
        MyRunnable myRunnable = new MyRunnable();
        MyCallable myCallable = new MyCallable();

        try {
            //第二步：执行线程带有返回值
            Future<String> CallableFuture = executor.submit(myCallable);
            //get()方法会使线程阻塞
            System.out.println("CallableFuture获取的结果：" + CallableFuture.get());

            System.out.println("CallableFuture isCancelled（是否已经取消）:" + CallableFuture.isCancelled());
            System.out.println("CallableFuture isDone（是否已经完成）:" + CallableFuture.isDone());

            //第二步：执行线程没有返回值
            Future<?> RunnableFuture = executor.submit(myRunnable);
            System.out.println("RunnableFuture获取的结果：" + RunnableFuture.get());

            System.out.println("RunnableFuture isCancelled（是否已经取消）:" + RunnableFuture.isCancelled());
            System.out.println("RunnableFuture isDone（是否已经完成）:" + RunnableFuture.isDone());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //TODO  第一步：创建线程MyCallable中是实现Callable的，泛型里面填返回值的类型，而Runnable则不用
        //TODO  第二步：通过线程池executor提交线程，返回对应的Future< ?>对象，若有返回值则填返回值类型，若无返回值则填？号
        //TODO Future管理类的执行结果，再一次的证明：Runnable和Callable的区别
//         我是Callable
//        CallableFuture获取的结果：10
//        CallableFuture isCancelled（是否已经取消）:false
//        CallableFutureisDone（是否已经完成）:true
//        我是Runnable
//        RunnableFuture获取的结果：null
//        RunnableFuture isCancelled（是否已经取消）:false
//        RunnableFuture isDone（是否已经完成）:true


    }


    //TODO FutureTask
    //TODO FutureTask是Future的实现类，而且不仅是Future又是Runnable，还包装了Callable，它是这两者的合体。
    private static void FutureTask() {
        //线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);
        //创建线程
        MyCallable callable = new MyCallable();
        //包装线程
        FutureTask<String> futureTask = new FutureTask<String>(callable);
        //执行线程
        executor.submit(futureTask);
        //获取结果
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
