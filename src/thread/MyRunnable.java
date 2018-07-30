package thread;

/**
 * Created by xiaolin on 2018/7/25.
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());//获取线程名


        System.out.println("我是Runnable");
    }
}
