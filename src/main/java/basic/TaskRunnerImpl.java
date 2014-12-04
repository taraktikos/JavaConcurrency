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
        TaskRunnerImpl _this = this;
        for (int i = 0; i < threadCount; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String name = Thread.currentThread().getName();
                        System.out.println(name + " started");
                        while (true) {
                            Task task;
                            synchronized (_this) {
                                while (queue.isEmpty()) {
                                    System.out.println(name + " waiting");
                                    queue.wait();
                                }
                                task = queue.remove();
                                _this.notify();
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

    public synchronized <V> boolean run(Task<V> task) {
        while (queue.size() == 20) {
            System.out.println("Queue have 20 elements and waiting");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                //Thread.currentThread().interrupt();
            }
        }
        System.out.println("added " + task.getTaskId());
        queue.add(task);
        notify();
        return true;
    }
}
