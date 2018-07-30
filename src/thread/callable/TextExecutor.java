package thread.callable;

import thread.MyRunnable;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TextExecutor {

    public static Executor THREAD_POOL_EXECUTOR = null;


    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;
    }


    public static void main(String[] args) {
        SerialExecutor serialExecutor = new SerialExecutor();
        serialExecutor.execute(new MyRunnable());
        serialExecutor.execute(new MyRunnable());
        serialExecutor.execute(new MyRunnable());


//        Thread.currentThread().getName()=main
//        我是Runnable
        MyRunnable myRunnable = new MyRunnable();
//        myRunnable.run();

//        Thread.currentThread().getName()=Thread-0
//        我是Runnable
        Thread thread = new Thread(myRunnable);
//        thread.start();


    }

    private static class SerialExecutor implements Executor {
        final ArrayDeque<Runnable> mTasks = new ArrayDeque<Runnable>();
        private Runnable mActive;

        public synchronized void execute(final Runnable r) {
            mTasks.offer(new Runnable() {
                public void run() {
                    try {
                        //TODO 这个r.run方法是调用MyRunnable的run方法，对象的方法，
                        //TODO 现在是在子线程，现在是模拟网络，睡眠5s，然后调用下一个线程，如此轮询
                        r.run();
                    } finally {
                        //
                        scheduleNext();
                    }
                }
            });

            //TODO 首次执行
            if (mActive == null) {
                scheduleNext();
            }
        }

        protected synchronized void scheduleNext() {
            if ((mActive = mTasks.poll()) != null) {
                THREAD_POOL_EXECUTOR.execute(mActive);
            }
        }
    }
}
