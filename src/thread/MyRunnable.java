package thread;

/**
 * Created by xiaolin on 2018/7/25.
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("我是Runnable");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());//获取线程名

    }
}
