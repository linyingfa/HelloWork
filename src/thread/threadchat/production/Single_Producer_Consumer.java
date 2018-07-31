package thread.threadchat.production;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by xiaolin on 2018/7/31.
 */
public class Single_Producer_Consumer {

    public static void main(String[] args) {
        KaoYaResource r = new KaoYaResource();

        Object lock = new Object();
        Object producerLock = new Object();
        Queue<String> messageQueue = new LinkedList<>();
        Producer pro = new Producer(messageQueue, lock, producerLock);
        Consumer con = new Consumer(messageQueue, lock, producerLock);
        //生产者线程
        Thread t0 = new Thread(pro);
        //消费者线程
        Thread t2 = new Thread(con);
        //启动线程
        t0.start();
        t2.start();



    }

}
