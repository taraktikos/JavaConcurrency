package basic;

import java.util.concurrent.TimeUnit;

public class TaskImpl<V> implements Task<V> {
    private static int counter = 0;
    private final int id = counter++;

    @Override
    public void run(V value) {
        System.out.println(getTaskId() + " started");
        try {
            System.out.println("value = " + value.toString());
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getTaskId() + " ended");
    }

    public String getTaskId() {
        return "Task-" + id;
    }
}
