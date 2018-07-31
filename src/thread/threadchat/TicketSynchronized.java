package thread.threadchat;

public class TicketSynchronized implements Runnable {

    private int num = 100;

    Object obj = new Object();

    /**
     * 当然代码同步也是要牺牲效率为前提的：
     * 同步的好处：解决了线程的安全问题。
     * 同步的弊端：相对降低了效率，因为同步外的线程的都会判断同步锁。
     * 同步的前提：同步中必须有多个线程并使用同一个锁。
     */
    @Override
    public void run() {
        while (true) {

            //TODO synchronized原理
            // 在java中，每一个对象有且仅有一个同步锁。这也意味着，同步锁是依赖于对象而存在。
            // 当我们调用某对象的synchronized方法时，就获取了该对象的同步锁。
            // 例如，synchronized(obj)就获取了“obj这个对象”的同步锁。
            // 不同线程对同步锁的访问是互斥的。
            // 也就是说，TODO 某时间点，对象的同步锁只能被一个线程获取到！!!!!!!!!!!!!
            // 通过同步锁，我们就能在多线程中，实现对“对象/方法”的互斥访问。
            // 例如，现在有两个线程A和线程B，它们都会访问“对象obj的同步锁”。
            // 假设，在某一时刻，线程A获取到“obj的同步锁”并在执行一些操作；
            // 而此时，线程B也企图获取“obj的同步锁” —— 线程B会获取失败，
            // 它必须等待，直到线程A释放了“该对象的同步锁”之后线程B才能获取到“obj的同步锁”从而才可以运行。


            //TODO synchronized基本规则
            //我们将synchronized的基本规则总结为下面3条，并通过实例对它们进行说明。
            //TODO 第一条:
            //当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
            // 其他线程对“该对象”的//TODO 该“synchronized方法”或者“synchronized代码块”的访问将被阻塞。
            //TODO 第二条:
            //当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
            // 其他线程仍然可以访问“该对象”的//TODO 非同步代码块。
            //TODO 第三条:
            //当一个线程访问“某对象”的“synchronized方法”或者“synchronized代码块”时，
            // 其他线程对“该对象”的//TODO 其他的“synchronized方法”或者“synchronized代码块”的访问将被阻塞。


            //TODO 3.线程间的通信机制
            //线程开始运行，拥有自己的栈空间，但是如果每个运行中的线程，
            //如果仅仅是孤立地运行，那么没有一点儿价值，或者是价值很小，
            //如果多线程能够相互配合完成工作的话，这将带来巨大的价值，
            //这也就是线程间的通信啦。在java中多线程间的通信使用的是//TODO 等待/通知机制来实现的。

            //TODO 3.1synchronied关键字等待/通知机制：
            // 是指一个线程A调用了对象O的wait()方法进入等待状态，
            // 而另一个线程B调用了对象O的notify()或者notifyAll()方法，
            // 线程A收到通知后从对象O的wait()方法返回，进而执行后续操作。
            // 上述的两个线程通过对象O来完成交互，而对象上的wait()和notify()/notifyAll()的关系
            // 就如同开关信号一样，用来完成等待方和通知方之间的交互工作。
            //当然这是针对synchronied关键字修饰的函数或代码块，
            // 因为要使用notify()/notifyAll(),wait()/wait(long),wait(long,int)这些方法的前提是对调用对象加锁，
            // 也就是说//TODO 只能在同步函数或者同步代码块中使用。

            //TODO 3.2条件对象的等待/通知机制：
            // 所谓的条件对象也就是配合前面我们分析的Lock锁对象，通过锁对象的条件对象来实现等待/通知机制。
            synchronized (obj) {
                if (num > 0) {
                    System.out.println(Thread.currentThread().getName() + ".....sale...." + num--);
                } else {
                    break;
                }
            }
        }
    }

}
