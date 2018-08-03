package thread.sky;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiaolin on 2018/8/2.
 */
public class LockTest2 {
    public static class Depot {
        private int size;        // 仓库的实际数量
        private Lock lock;        // 独占锁

        public Depot() {
            this.size = 0;
            this.lock = new ReentrantLock();
        }

        public void produce(int val) {
            System.out.printf("%s 准备生产(%d)个  size=%d\n", Thread.currentThread().getName(), val, size);
//        lock.lock();
//        try {
            size += val;
            System.out.printf("%s 开始生产(%d)个 size=%d\n", Thread.currentThread().getName(), val, size);
//        } catch (InterruptedException e) {
//        } finally {
//            lock.unlock();
//        }
        }

        public void consume(int val) {
            System.out.printf("%s 准备消费(%d)  size=%d\n", Thread.currentThread().getName(), val, size);
//        lock.lock();
//        try {
            size -= val;
//            System.out.printf("%s consume(%d) <-- size=%d\n", Thread.currentThread().getName(), val, size);
            System.out.printf("%s 开始消费(%d)  size=%d\n", Thread.currentThread().getName(), val, size);
//        } finally {
//            lock.unlock();
//        }
        }
    }


    // 生产者
    public static class Producer {
        private Depot depot;

        public Producer(Depot depot) {
            this.depot = depot;
        }

        // 消费产品：新建一个线程向仓库中生产产品。
        public void produce(final int val) {
            new Thread() {
                public void run() {
                    depot.produce(val);
                }
            }.start();
        }
    }

    // 消费者
    public static class Customer {
        private Depot depot;

        public Customer(Depot depot) {
            this.depot = depot;
        }

        // 消费产品：新建一个线程从仓库中消费产品。
        public void consume(final int val) {
            new Thread() {
                public void run() {
                    depot.consume(val);
                }
            }.start();
        }
    }

    public static void main(String[] args) {
        Depot mDepot = new Depot();
        Producer mPro = new Producer(mDepot);
        Customer mCus = new Customer(mDepot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);

        //“示例2”在“示例1”的基础上去掉了lock锁。
        // 在“示例2”中，仓库中最终剩余的产品是-60，
        // 而不是我们期望的50。原因是我们没有实现对仓库的互斥访问。


    }

}
