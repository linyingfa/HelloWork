package thread.threadchat.production;


/**
 * 死锁示例
 */
public class DeadLockDemo {


    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        DeadLockDemo deadLock = new DeadLockDemo();
        while (true) {
            deadLock.deadLock();
        }
    }

    //当t1线程拿到锁A后，睡眠2秒，
    // 此时线程t2刚好拿到了B锁，
    // 接着要获取A锁，但是此时A锁正好被t1线程持有，
    // 因此只能等待t1线程释放锁A，
    // 但遗憾的是在t1线程内又要求获取到B锁，而B锁此时又被t2线程持有，
    // 到此结果就是t1线程拿到了锁A同时在等待t2线程释放锁B，
    // 而t2线程获取到了锁B也同时在等待t1线程释放锁A，
    // 彼此等待也就造成了线程死锁问题。
    // 虽然我们现实中一般不会向上面那么写出那样的代码，但是有些更为复杂的场景中，我们可能会遇到这样的问题，
    // 比如t1拿了锁之后，因为一些异常情况没有释放锁（死循环），也可能t1拿到一个数据库锁，释放锁的时候抛出了异常，
    // 没有释放等等，所以我们应该在写代码的时候多考虑死锁的情况，
    // 这样才能有效预防死锁程序的出现。下面我们介绍一下避免死锁的几个常见方法：
    // TODO 1.避免一个线程同时获取多个锁。
    // TODO 2.避免在一个资源内占用多个 资源，尽量保证每个锁只占用一个资源。
    // TODO 3.尝试使用定时锁，使用tryLock(timeout)来代替使用内部锁机制。
    // TODO 4.对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况。
    // TODO 5.避免同步嵌套的发生
    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @SuppressWarnings("static-access")
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.currentThread().sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (B) {
                    System.out.println("1");
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("2");
                    }
                }
            }
        });

        //启动线程
        t1.start();
        t2.start();
    }

}
