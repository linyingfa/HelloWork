package thread.sky;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiaolin on 2018/8/2.
 */
public class LockTest1 {

    // 仓库
    public static class Depot {
        private int size;        // 仓库的实际数量
        private Lock lock;        // 独占锁

        public Depot() {
            this.size = 0;
            this.lock = new ReentrantLock();
        }

        public void produce(int val) {
            System.out.printf("%s 准备生产(%d)个  size=%d\n", Thread.currentThread().getName(), val, size);
            lock.lock();
            try {
                size += val;
                System.out.printf("%s 开始生产(%d)个 size=%d\n", Thread.currentThread().getName(), val, size);
            } finally {
                lock.unlock();
            }
        }

        public void consume(int val) {
            System.out.printf("%s 准备消费(%d)  size=%d\n", Thread.currentThread().getName(), val, size);
            lock.lock();
            try {
                size -= val;
                System.out.printf("%s 开始消费(%d)  size=%d\n", Thread.currentThread().getName(), val, size);
            } finally {
                lock.unlock();
            }
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
/*//        结果分析：
//TODO        (01) Depot 是个仓库。通过produce()能往仓库中生产货物，通过consume()能消费仓库中的货物。
//TODO        通过独占锁lock实现对仓库的互斥访问：在操作(生产/消费)仓库中货品前，会先通过lock()锁住仓库，操作完之后再通过unlock()解锁。
//TODO        (02) Producer是生产者类。调用Producer中的produce()函数可以新建一个线程往仓库中生产产品。
//TODO        (03) Customer是消费者类。调用Customer中的consume()函数可以新建一个线程消费仓库中的产品。
//TODO        (04) 在主线程main中，我们会新建1个生产者mPro，同时新建1个消费者mCus。它们分别向仓库中生产/消费产品。
//TODO        根据main中的生产/消费数量，仓库最终剩余的产品应该是50。运行结果是符合我们预期的！
//
// TODO        这个模型存在两个问题：
// TODO       (01) 现实中，仓库的容量不可能为负数。但是，此模型中的仓库容量可以为负数，这与现实相矛盾！
// TODO       (02) 现实中，仓库的容量是有限制的。但是，此模型中的容量确实没有限制的！
// TODO       这两个问题，我们稍微会讲到如何解决。现在，先看个简单的示例2；通过对比“示例1”和“示例2”,我们能更清晰的认识lock(),unlock()的用途。*/


/*        Thread-1 准备生产(120)个  size=0
        Thread-3 准备消费(150)  size=0
        Thread-4 准备生产(110)个  size=0
        Thread-0 准备生产(60)个  size=0
        Thread-2 准备消费(90)  size=0
        Thread-1 开始生产(120)个 size=120
        Thread-3 开始消费(150)  size=-30  //TODO  现实中，仓库的容量不可能为负数。
        Thread-4 开始生产(110)个 size=80
        Thread-0 开始生产(60)个 size=140
        Thread-2 开始消费(90)  size=50*/
    }
}
