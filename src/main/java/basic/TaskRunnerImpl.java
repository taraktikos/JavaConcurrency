package basic;

import java.util.LinkedList;
import java.util.Queue;

public class TaskRunnerImpl implements TaskRunner {

    private final Queue<Task> queue = new LinkedList<>();

    TaskRunnerImpl() {
        this(5);
    }

    TaskRunnerImpl(int threadCount) {
        System.out.println(queue.size());
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String name = Thread.currentThread().getName();
                        System.out.println(name + " started");
                        while (true) {
                            Task task;
                            synchronized (queue) {
                                while (queue.isEmpty()) {
                                    System.out.println(name + " waiting");
                                    queue.wait();
                                }
                                task = queue.remove();
                            }
                            task.run();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        //Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }
    }

    public <V> boolean run(Task<V> task) {
        synchronized (queue) {
            System.out.println("added " + task.getTaskId());
            queue.add(task);
            queue.notify();
        }
        return true;
    }
}
