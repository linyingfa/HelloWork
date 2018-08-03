package thread.sky.test;

public class Test1 {

    /**
     * 自旋实现的等待通知
     */
    private static volatile int condition = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread A = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!(condition == 1)) {
                    // 条件不满足，自旋
                }
                System.out.println("a executed");
            }
        });

        A.start();
        Thread.sleep(2000);
        condition = 1;
    }
}
