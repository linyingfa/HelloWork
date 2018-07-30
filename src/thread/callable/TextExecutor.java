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

//        execute--Thread.currentThread().getName()=pool-1-thread-1
//                Thread.currentThread().getName()=pool-1-thread-1
//        我是Runnable
//        execute--Thread.currentThread().getName()=pool-1-thread-2
//                Thread.currentThread().getName()=pool-1-thread-2
//        我是Runnable
//        execute--Thread.currentThread().getName()=pool-1-thread-1
//                Thread.currentThread().getName()=pool-1-thread-1
//        我是Runnable

        public synchronized void execute(final Runnable r) {
            mTasks.offer(new Runnable() {
                public void run() {
                    try {
                        System.out.println("execute--Thread.currentThread().getName()=" + Thread.currentThread().getName());//获取线程名
                        r.run();
                    } finally {
                        scheduleNext();
                    }
                }
            });
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
