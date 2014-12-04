package basic;

import java.util.concurrent.TimeUnit;

public class Client {
    private TaskRunner taskRunner;

    Client (TaskRunner taskRunner) {
        this.taskRunner = taskRunner;
    }

    public void generate() {
        int i = 1;
        while (i < 100) {
//            if (i % 10 == 0) {
//                try {
//                    TimeUnit.SECONDS.sleep(10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            taskRunner.run(new Task<>(i++));
        }
    }
}
