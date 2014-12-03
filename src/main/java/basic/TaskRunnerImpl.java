package basic;

import java.util.LinkedList;
import java.util.Queue;

public class TaskRunnerImpl implements TaskRunner {

    private final Queue<TaskEntry<Task, Object>> queue = new LinkedList<>();
    private final int threadCount;

    TaskRunnerImpl() {
        this(5);
    }

    TaskRunnerImpl(int threadCount) {
        this.threadCount = threadCount;
    }

    public <V> boolean run(Task<V> task, V value) {
        queue.add(new TaskEntry<>(task, value));
        System.out.println(queue.size());
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (existTask()) {
                        TaskEntry<Task, Object> taskEntry = queue.remove();
                        taskEntry.getKey().run(taskEntry.getValue());
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
