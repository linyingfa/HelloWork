package thread.sky.test;

public class Test2 {

    private static volatile int condition = 0;
    private static final Object lock = new Object();
    /**
     * Object提供的等待通知
     */
    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    while (!(condition == 1)) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                    System.out.println("a executed by notify");
                }
            }
        });
        A.start();
        Thread.sleep(2000);
        condition = 1;
        synchronized (lock) {
            lock.notify();
        }
    }
}
