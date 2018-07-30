package thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiaolin on 2018/7/25.
 */
public class MyRunnable implements Runnable {

    static AtomicInteger atomicInteger = new AtomicInteger(0);
    int index = 0;

    public MyRunnable() {
        index = atomicInteger.getAndIncrement();
    }

    public MyRunnable(int index) {
//        index = atomicInteger.getAndIncrement();
        this.index = index;
    }

    @Override
    public void run() {
        System.out.println("我是Runnable  " + index);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());//获取线程名

    }
}
