package thread.threadchat.production;

/**
 * Created by xiaolin on 2018/7/31.
 */
public class KaoYaResource {


    //在这个类中我们有两个synchronized的同步方法，一个是生产烤鸭的，一个是消费烤鸭的，
    //之所以需要同步是因为我们操作了共享数据count，
    //同时为了保证生产烤鸭后才能消费也就是生产一只烤鸭后才能消费一只烤鸭，
    //我们使用了等待/通知机制，wait()和notify()。当第一次运行生产现场时调用生产的方法，
    //TODO 此时有一只烤鸭，即flag=false，无需等待，
    //因此我们设置可消费的烤鸭名称然后改变flag=true，
    //同时通知消费线程可以消费烤鸭了，即使此时生产线程再次抢到执行权，
    //因为flag=true，所以生产线程会进入等待阻塞状态，
    //消费线程被唤醒后就进入消费方法，消费完成后，
    //又改变标志flag=false，
    //通知生产线程可以生产烤鸭了.........以此循环。


    private String name;
    private int count = 1;//烤鸭的初始数量
    private boolean flag = false;//判断是否有需要线程等待的标志

    /**
     * 生产烤鸭
     */
    public synchronized void product(String name) {
        if (flag) {
            //此时有烤鸭，等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace()
                ;
            }
        }
        this.name = name + count;//设置烤鸭的名称
        count++;
        System.out.println(Thread.currentThread().getName() + "...生产者..." + this.name);
        flag = true;//有烤鸭后改变标志
        notifyAll();//通知消费线程可以消费了
    }

    /**
     * 消费烤鸭
     */
    public synchronized void consume() {
        if (flag) {//如果没有烤鸭就等待
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println(Thread.currentThread().getName() + "...消费者........" + this.name);//消费烤鸭1
        flag = false;
        notifyAll();//通知生产者生产烤鸭
    }

}
