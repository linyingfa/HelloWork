package thread.threadchat.production;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by xiaolin on 2018/7/31.
 * 生产者线程
 */
public class Producer implements Runnable {
    private Queue<String> messageQueue;
    Object lock;
    Object producerLock;
    private static AtomicInteger ato = new AtomicInteger(0);

    Producer(Queue<String> messageQueue, Object lock, Object producerLock) {
        this.messageQueue = messageQueue;
        this.lock = lock;
        this.producerLock = producerLock;
    }

    public void run() {
        while (true) {
            String msg = "生产消息  " + ato.getAndIncrement();
            System.out.println("--------- " + msg);

            messageQueue.add(msg);
//            doJob(1500);
            synchronized (lock) {
                System.out.println("--------------aaa111");
                lock.notify();
                System.out.println("--------------aaa22222");
            }


            // sfsfs
            synchronized (producerLock) {
                try {
                    producerLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public void doJob(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
