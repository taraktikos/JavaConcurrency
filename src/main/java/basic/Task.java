package basic;

import java.util.concurrent.TimeUnit;

public class Task<V> implements Runnable {
    private static int counter = 1;
    private final int id = counter++;
    private final V value;

    public Task(V value) {
        this.value = value;
    }

    @Override
    public void run() {
        System.out.println(getTaskId() + " started");
        try {
            System.out.println(getTaskId() + " value = " + value.toString());
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getTaskId() + " ended");
    }

    public String getTaskId() {
        return "Task-" + id;
    }
}
