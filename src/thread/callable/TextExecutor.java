package thread.callable;

import thread.MyRunnable;

import java.util.ArrayDeque;
import java.util.concurrent.*;

public class TextExecutor {

    public static Executor THREAD_POOL_EXECUTOR = null;


    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());//TODO SynchronousQueue 不保存任务runable
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;
    }


    public static void main(String[] args) {
//        Thread.currentThread().getName()=main
//        我是Runnable
        MyRunnable myRunnable = new MyRunnable();
//        myRunnable.run();

//        Thread.currentThread().getName()=Thread-0
//        我是Runnable
        Thread thread = new Thread(myRunnable);
//        thread.start();


        SerialExecutor serialExecutor = new SerialExecutor();
        //TODO 串行执行 方法1
  /*      for (int i = 0; i < 100; i++) {
            serialExecutor.submit(new MyRunnable(i));
        }
        serialExecutor.start();*/


        //TODO 串行执行 方法2
        for (int i = 0; i < 100; i++) {
            serialExecutor.execute(new MyRunnable(i));
        }
    }

    private static class SerialExecutor implements Executor {
        final ArrayDeque<Runnable> mTasks = new ArrayDeque<Runnable>();
        private Runnable mActive;

        BlockingQueue<Runnable> mT = new LinkedBlockingQueue<>();


        public void submit(Runnable runnable) {
            mT.add(runnable);
        }

        public synchronized void start() {
            THREAD_POOL_EXECUTOR.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Runnable runnable = mT.take();
                            runnable.run();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        public synchronized void execute(final Runnable r) {
            mTasks.offer(new Runnable() {
                public void run() {
                    try {
                        //TODO 这个r.run方法是调用MyRunnable的run方法，对象的方法，
                        //TODO 现在是在子线程，现在是模拟网络，睡眠5s，然后调用下一个线程，如此轮询
                        r.run();
                    } finally {
                        //TODO 一个线程执行完了，再执行下一个
                        scheduleNext();
                    }
                }
            });
//
//            //TODO serialExecutor.execute(new MyRunnable());
//            //TODO 首次执行，因为是在主线程调用提交任务，在子线程执行过程中是耗时的，可能会在主线程提交多个
            //TODO  若线程执行中，就不再执行了
            if (mActive == null) {
                scheduleNext();
            }
        }

        //
        protected synchronized void scheduleNext() {
            if ((mActive = mTasks.poll()) != null) {
                THREAD_POOL_EXECUTOR.execute(mActive);
            }
        }
    }
}
