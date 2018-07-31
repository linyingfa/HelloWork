package thread.threadchat.production;

import java.util.Queue;

/**
 * Created by xiaolin on 2018/7/31.
 * 消费者线程
 */
public class Consumer implements Runnable {
    private Queue<String> messageQueue ;
    Object lock;
    Object producerLock;

    Consumer(Queue<String> messageQueue, Object lock, Object producerLock ) {
        this.messageQueue = messageQueue;
        this.lock = lock;
        this.producerLock = producerLock;
    }

    public void run() {
        while (true) {

            String message = messageQueue.poll();
            if (message == null) {
                System.out.println("------------- null");
                synchronized (lock) {
                    System.out.println("------------------------sss111");
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("------------------------sss222");
                }
                continue;
            }
            System.out.println("消费:  " + message);
            synchronized (producerLock) {
                producerLock.notify();
            }
            //  dsfd
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
