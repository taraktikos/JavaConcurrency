package basic;

public class Client {
    private TaskRunner taskRunner;

    Client (TaskRunner taskRunner) {
        this.taskRunner = taskRunner;
    }

    public void generate() {
        int i = 1;
        while (i < 100) {
            taskRunner.run(new Task<>(i++));
        }
    }
}
