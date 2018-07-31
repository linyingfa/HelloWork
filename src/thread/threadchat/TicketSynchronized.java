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

            //TODO 在java中，每一个对象有且仅有一个同步锁。这也意味着，同步锁是依赖于对象而存在。
            //TODO  当我们调用某对象的synchronized方法时，就获取了该对象的同步锁。
            // 例如，synchronized(obj)就获取了“obj这个对象”的同步锁。
            // 不同线程对同步锁的访问是互斥的。
            // 也就是说，TODO 某时间点，对象的同步锁只能被一个线程获取到！!!!!!!!!!!!!
            // 通过同步锁，我们就能在多线程中，实现对“对象/方法”的互斥访问。
            // 例如，现在有两个线程A和线程B，它们都会访问“对象obj的同步锁”。
            // 假设，在某一时刻，线程A获取到“obj的同步锁”并在执行一些操作；
            // 而此时，线程B也企图获取“obj的同步锁” —— 线程B会获取失败，
            // 它必须等待，直到线程A释放了“该对象的同步锁”之后线程B才能获取到“obj的同步锁”从而才可以运行。
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
