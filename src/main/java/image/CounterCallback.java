package image;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterCallback implements Callback {

    private AtomicInteger count;
    private Callback callback;

    public CounterCallback(int count, Callback callback) {
        this.count = new AtomicInteger(count);
        this.callback = callback;
    }

    @Override
    public void call() {
        if (count.decrementAndGet() == 0) {
            callback.call();
        }
    }
}
