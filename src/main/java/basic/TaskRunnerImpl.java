package basic;

import java.util.LinkedList;
import java.util.Queue;

public class TaskRunnerImpl implements TaskRunner {

    Queue<Task> queue = new LinkedList<>();

    public <V> boolean run(Task<V> task, V value) {
        queue.add(task);

        queue.remove().run(value);
        return true;
    }
}
