package thread;

import java.util.concurrent.Callable;

/**
 * Created by xiaolin on 2018/7/25.
 */
public class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(2000);
        return "我是Callable返回值";
    }
}
