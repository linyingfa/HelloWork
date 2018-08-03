package thread.sky.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test4 {

    private static volatile int condition = 0;
    private static Lock lock = new ReentrantLock();
    private static Condition lockCondition = lock.newCondition();

    /**
     * 显示锁提供的等待通知
     * 可以看到通过 lock.newCondition() 可以获得到 lock 对应的一个Condition对象lockCondition ，
     * lockCondition的await()、signal()方法分别对应之前的Object的wait()和notify()方法。整体上和Object的等待通知是类似的。
     */
    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    while (!(condition == 1)) {
                        lockCondition.await();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
                System.out.println("a executed by condition");
            }
        });
        A.start();
        Thread.sleep(2000);
        condition = 1;
        lock.lock();
        try {
            lockCondition.signal();
        } finally {
            lock.unlock();
        }
    }
}
