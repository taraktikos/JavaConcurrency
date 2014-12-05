package basic;

import java.util.LinkedList;
import java.util.Queue;

public class TaskRunnerImpl implements TaskRunner {

    private final Queue<Task> queue = new LinkedList<>();
    private final int queueSize;
    private final Object lockFull = new Object();
    private final Object lockEmpty = new Object();

    TaskRunnerImpl() {
        this(2, 20);
    }

    TaskRunnerImpl(int threadCount, int queueSize) {
        this.queueSize = queueSize;
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
                            synchronized (lockEmpty) {
                                while (queue.isEmpty()) {
                                    System.out.println(name + " waiting");
                                    lockEmpty.wait();
                                }
                                task = queue.remove();
                            }
                            synchronized (lockFull) {
                                lockFull.notifyAll();
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
        synchronized (lockFull) {
            while (queue.size() == queueSize) {
                System.out.println("Queue have " + queueSize + " elements and waiting");
                try {
                    lockFull.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //Thread.currentThread().interrupt();
                }
            }
            synchronized (lockEmpty) {
                System.out.println("added " + task.getTaskId());
                queue.add(task);
                lockEmpty.notifyAll();
            }
        }
        return true;
    }
}
