package thread.threadchat;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟卖票系统，该案例只考虑单方面卖票，其他情况暂时不考虑
 */
public class TicketDemo {

    public static void main(String[] args) {

//        Ticket t = new Ticket();//创建一个线程任务对象。
//        TicketLock t = new TicketLock();//带锁的任务
        TicketSynchronized t = new TicketSynchronized();
        //创建4个线程同时卖票
        Thread t1 = new Thread(t);
        Thread t2 = new Thread(t);
        Thread t3 = new Thread(t);
        Thread t4 = new Thread(t);
        //启动线程
        t1.start();
        t2.start();
        t3.start();
        t4.start();

    }

    private void testReentrantLock() {
        //todo 把解锁操作放在finally代码块内这个十分重要。如果在临界区的代码抛出异常，锁必须被释放。否则，其他线程将永远阻塞。
        //TODO 1.同步执行的代码跟synchronized类似功能：

        ReentrantLock lock1 = new ReentrantLock(); //参数默认false，不公平锁
        ReentrantLock lock2 = new ReentrantLock(true); //公平锁

        lock1.lock(); //如果被其它资源锁定，会在此等待锁释放，达到暂停的效果
        try {
            //操作
        } finally {
            lock1.unlock();  //释放锁
        }

        lock2.lock(); //如果被其它资源锁定，会在此等待锁释放，达到暂停的效果
        try {
            //操作
        } finally {
            lock2.unlock();  //释放锁
        }

        //TODO 2.防止重复执行代码：
        ReentrantLock lock3 = new ReentrantLock();
        if (lock3.tryLock()) {  //如果已经被lock，则立即返回false不会等待，达到忽略操作的效果
            try {
                //操作
            } finally {
                lock3.unlock();
            }
        }

        //TODO 3.尝试等待执行的代码：
        ReentrantLock lock4 = new ReentrantLock(true); //公平锁
        try {
            if (lock4.tryLock(5, TimeUnit.SECONDS)) {
                //如果已经被lock，尝试等待5s，看是否可以获得锁，如果5s后仍然无法获得锁则返回false继续执行
                try {
                    //操作
                } finally {
                    lock4.unlock();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace(); //当前线程被中断时(interrupt)，会抛InterruptedException
        }

    }

    private void testsynchronized() {
        //TODO 2.2通过synchronied关键字的方式解决线程安全问题
        //在Java中内置了语言级的同步原语－synchronized，这个可以大大简化了Java中多线程同步的使用。
        // 从JAVA SE1.0开始，java中的每一个对象都有一个内部锁，
        // 如果一个方法使用synchronized关键字进行声明，那么这个对象将保护整个方法，
        // 也就是说调用该方法线程必须获得内部的对象锁。

        /*        public synchronized void method(){
                    //method body
                   }
        */
        //  TODO 等价于

        /*      Lock ticketLock = new ReentrantLock();
                public void method() {
                    ticketLock.lock();
                    try {
                        //.......
                    } finally {
                        ticketLock.unlock();
                    }
                }
        */

        //当然，要理解这一代码，我们必须知道每个对象有一个内部锁，并且该锁有一个内部条件。
        // 由锁来管理那些试图进入synchronized方法的线程，
        // 由条件来管理那些调     用wait的线程(wait()/notifyAll/notify())。
        // 同时我们必须明白一旦有一个线程通过synchronied方法获取到内部锁，
        // 该类的所有synchronied方法或者代码块都无法被其他线程访问直到当前线程释放了内部锁。

        Object obj = new Object();
        synchronized (obj) {
            //需要同步的代码
        }

    }


}
