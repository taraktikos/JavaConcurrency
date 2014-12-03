package basic;

import java.util.LinkedList;
import java.util.Queue;

public class TaskRunnerImpl implements TaskRunner {

    private final Queue<Task> queue = new LinkedList<>();

    TaskRunnerImpl() {
        this(2);
    }

    TaskRunnerImpl(int threadCount) {
        System.out.println(queue.size());
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Thread started");
                    while (existTask()) {
                        queue.remove().run();
                    }
                }
            }).start();
        }
    }

    public <V> boolean run(Task<V> task) {
        return queue.add(task);
    }

    public boolean existTask() {
        return queue.size() > 0;
    }
}
