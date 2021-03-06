package thread.sky;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by xiaolin on 2018/8/2.
 */
public class LockTest3 {
    public static class Depot {
        private int capacity;    // 仓库的容量
        private int size;        // 仓库的实际数量
        private Lock lock;        // 独占锁
        private Condition fullCondtion;            // 生产条件
        private Condition emptyCondtion;        // 消费条件

        public Depot(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.lock = new ReentrantLock();
            this.fullCondtion = lock.newCondition();
            this.emptyCondtion = lock.newCondition();
        }

        public void produce(int val) {
            System.out.printf("%s 准备生产(%3d) --> , size=%3d\n", Thread.currentThread().getName(), val, size);
            lock.lock();
            try {
                // left 表示“想要生产的数量”(有可能生产量太多，需多此生产)
                int left = val;
                while (left > 0) {
                    // 库存已满时，等待“消费者”消费产品。
                    // 仓库的容量  capacity;
                    // 仓库的实际数量  size;
                    while (size >= capacity)//满仓
                        fullCondtion.await();
                    // 获取“实际生产的数量”(即库存中新增的数量)
                    // 如果“库存”+“想要生产的数量”>“总的容量”，则“实际增量”=“总的容量”-“当前容量”。(此时填满仓库)
                    // 否则“实际增量”=“想要生产的数量”
                    //TODO 仓库实际的数量+需要生产的数量>仓库容量(仓库容量-实际仓库数量作为需要生产的数量：需要生产的数量)
                    //TODO 仓库容量默认是100
                    int inc = (size + left) > capacity ? (capacity - size) : left;
                    //TODO 更新实际数量
                    size += inc;
                    //
                    left -= inc;
                    System.out.printf("%s 开始生产(%3d) --> left=%3d, inc=%3d, size=%3d\n", Thread.currentThread().getName(), val, left, inc, size);
                    // 通知“消费者”可以消费了。
                    emptyCondtion.signal();
                }
            } catch (InterruptedException e) {
            } finally {
                lock.unlock();
            }
        }

        public void consume(int val) {
            System.out.printf("%s 准备消费(%3d) <--  size=%3d\n", Thread.currentThread().getName(), val, size);
            lock.lock();
            try {
                // left 表示“客户要消费数量”(有可能消费量太大，库存不够，需多此消费)
                int left = val;
                while (left > 0) {
                    // 库存为0时，等待“生产者”生产产品。
                    while (size <= 0)
                        emptyCondtion.await();
                    // 获取“实际消费的数量”(即库存中实际减少的数量)
                    // 如果“库存”<“客户要消费的数量”，则“实际消费量”=“库存”；
                    // 否则，“实际消费量”=“客户要消费的数量”。
                    int dec = (size < left) ? size : left;
                    size -= dec;
                    left -= dec;
                    System.out.printf("%s 开始消费(%3d) <-- left=%3d, dec=%3d, size=%3d\n", Thread.currentThread().getName(), val, left, dec, size);
                    fullCondtion.signal();
                }
            } catch (InterruptedException e) {
            } finally {
                lock.unlock();
            }
        }

        public String toString() {
            return "capacity:" + capacity + ", actual size:" + size;
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
        Depot mDepot = new Depot(100);
        Producer mPro = new Producer(mDepot);
        Customer mCus = new Customer(mDepot);

        mPro.produce(60);
        mPro.produce(120);
        mCus.consume(90);
        mCus.consume(150);
        mPro.produce(110);

//        在“示例3”中，我们通过Condition去解决“示例1”中的两个问题：“仓库的容量不可能为负数”以及“仓库的容量是有限制的”。
//        解决该问题是通过Condition。Condition是需要和Lock联合使用的：通过Condition中的await()方法，能让线程阻塞[类似于wait()]；
//        通过Condition的signal()方法，能让唤醒线程[类似于notify()]。
    }


}
