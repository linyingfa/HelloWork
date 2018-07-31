package thread.threadchat;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TicketLock implements Runnable {

    //创建锁对象
    private Lock ticketLock = new ReentrantLock();
    //当前拥有的票数
    private int num = 100;

    @Override
    public void run() {
        while (true) {
            try {
                ticketLock.lock();//获取锁
                if (num > 0) {
                    Thread.sleep(5000);//输出卖票信息
                    System.out.println(Thread.currentThread().getName() + ".....sale...." + num--);
                } else {
                    break;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();//出现异常就中断
            } finally {
                ticketLock.unlock();//释放锁
            }
        }
    }

}
