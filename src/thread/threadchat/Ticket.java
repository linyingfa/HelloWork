package thread.threadchat;

/**
 * 产生线程同步(线程安全)问题的条件有两个：1.多个线程在操作共享的数据（num），2.操作共享数据的线程代码有多条（4条线程）；
 * <p>
 * 解决思路：将多条操作共享数据的线程代码封装起来，当有线程在执行这些代码的时候，其他线程时不可以参与运算的。
 * 必须要当前线程把这些代码都执行完毕后，其他线程才可以参与运算。 好了，思路知道了，我们就用java代码的方式来解决这个问题
 * <p>
 * 2.解决线程同步的两种典型方案
 * 在java中有两种机制可以防止线程安全的发生，Java语言提供了一个synchronized关键字来解决这问题，
 * 同时在Java SE5.0引入了Lock锁对象的相关类，接下来我们分别介绍这两种方法
 */
public class Ticket implements Runnable {
    //当前拥有的票数
    private int num = 200;

    @Override
    public void run() {
        while (true) {
            if (num > 0) {
                //输出卖票信息
                System.out.println(Thread.currentThread().getName() + ".....sale...." + num--);
            } else {
                return;
            }
        }
    }

}
