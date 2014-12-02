package basic;

import java.util.LinkedList;
import java.util.Queue;

public class TaskRunnerImpl implements TaskRunner {

    Queue<Task> queue = new LinkedList<>();
    private final int threadCount;

    TaskRunnerImpl() {
        this(5);
    }

    TaskRunnerImpl(int threadCount) {
        this.threadCount = threadCount;
    }

    public <V> boolean run(Task<V> task, V value) {
        queue.add(task);
        System.out.println(queue.size());
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (existTask()) {
                        queue.remove().run(value);
                    }
                }
            }).start();
        }

        return true;
    }

    public boolean existTask() {
        return queue.size() > 0;
    }
}
