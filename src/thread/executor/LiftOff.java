package thread.executor;


//TODO 分析：
//        （1）如果当前运行线程数少corePoolSize，则创建一个新的线程来执行任务。
//        （2）如果当前线程池的运行线程数等于corePoolSize，那么后面提交的任务将加入LinkedBlockingQueue。
//        （3）线程在执行完图中的1(核心线程)后，会在循环中反复从LinkedBlockingQueue获取任务来执行。
//             这里还有点要说明的是FixedThreadPool使用的是//TODO 无界队列LinkedBlockingQueue作为线程池的工作队列（队列容量为Integer.MAX_VALUE）
//       //TODO 使用该队列作为工作队列会对线程池产生如下影响:
//        （1.1）当前线程池中的线程数量达到corePoolSize后，新的任务将在无界队列中等待(LinkedBlockingQueue)。
//        （2.2）由于我们使用的是无界队列，所以参数maximumPoolSize和keepAliveTime无效。
//        （3.3）由于使用无界队列，运行中的FixedThreadPool不会拒绝任务（当然此时是未执行shutdown和shutdownNow方法），
//             所以不会去调用RejectExecutionHandler的rejectExecution方法抛出异常。//TODO 不会拒绝

public class LiftOff implements Runnable {
    protected int countDown = 10; //Default
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff() {
    }

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" + (countDown > 0 ? countDown : "LiftOff!") + ") ";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println(status());
            Thread.yield();
        }

    }

}
