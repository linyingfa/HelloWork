package thread.sky.test;

public class Test5 {


    public static void main(String[] args) {
    /*    BoundedQueue<String> boundedQueue = new BoundedQueue<>(20);
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 35; i++) {
                    boundedQueue.add("i=" + i);
                }
            }
        }).start();
        boundedQueue.remove();*/


        /**
         * Java多线程面试题来让大家练习熟悉熟悉：
         * 子线程循环10次，接着主线程循环15次，接着又回到子线程循环10次，接着再回到主线程又循环15次，如此循环50次
         */
        Object lock = new Object();
        //子线程
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (lock) {
                        System.out.println("子线程");
                        for (int j = 0; j < 1; j++) {
                            System.out.println("子循环循环第" + (j + 1) + "次");
                        }
                        //唤醒
                        lock.notify();
                        //等待
                        try {
                            lock.wait();
                            System.out.println("子线程22");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }.start();

        //主线程
        for (int i = 0; i < 10; i++) {
            synchronized (lock) {
                //等待
                try {
//                    不管是主线程先运行还是子线程运行，两个线程只能同时进入synchronized (lock)一个锁中。
//                    由于是子线程先运行：1、当主线程先进入synchronized (lock)锁时，它就必须是等待，
//                    而子线程开始运行输出，输出后就唤醒主线程。2、当子线程先运行的话，那就直接输出，然后等待主线程的运行输出
                    System.out.println("主线程");
                    lock.wait();
                    System.out.println("主线程2222");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for (int j = 0; j < 1; j++) {
                    System.out.println("主循环循环第" + (j + 1) + "次");
                }
                //唤醒
                lock.notify();
            }
        }


    }
}
