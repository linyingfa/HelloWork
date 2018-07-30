package thread.callable;

import java.util.concurrent.*;

public class CallableTest {

    public static void main(String[] args) {
//        CreateExecutorone();
        CreateExecutortwo();
    }


    //第一种使用方式
    private static void CreateExecutorone() {
        //创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        //创建Callable对象任务
        CallableDemo calTask = new CallableDemo();
        //提交任务并获取执行结果
        Future<Integer> future = es.submit(calTask);
        //关闭线程池
        es.shutdown();
    }

    //第二中使用方式

    /**
     * Callable子线程开始计算啦！
     * 主线程在执行其他任务
     * Callable子线程计算结束！
     * futureTask.get()-->12497500
     * 主线程在执行完成
     */
    private static void CreateExecutortwo() {
        //创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        //创建Callable对象任务
        CallableDemo calTask = new CallableDemo();
        //创建FutureTask
        //Future接口是用来获取异步计算结果的，说白了就是对具体的Runnable或者Callable对象任务执行的结果进行
        // 获取(get()),取消(cancel()),判断是否完成等操作。
//        总得来说Future有以下3点作用：
        //   TODO      能够中断执行中的任务
        //   TODO      判断任务是否执行完成
        //   TODO      获取任务执行完成后额结果。
//      但是Future只是接口，我们根本无法将其创建为对象，于官方又给我们提供了其实现类FutureTask，
//        这里我们要知道前面两个接口的介绍都只为此类做铺垫，毕竟AsncyTask中使用到的对象是FutureTask。


        FutureTask<Integer> futureTask = new FutureTask<>(calTask);
        //执行任务
        Future<?> future = es.submit(futureTask);
        //关闭线程池
        es.shutdown();
        future.isCancelled();
        future.isDone();

//        future.get();
        try {
            Thread.sleep(2000);
            System.out.println("主线程在执行其他任务");

            if (futureTask.get() != null) {
                //输出获取到的结果
                System.out.println("futureTask.get()-->" + futureTask.get());
            } else {
                //输出获取到的结果
                System.out.println("futureTask.get()未获取到结果");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行完成");
    }


}
