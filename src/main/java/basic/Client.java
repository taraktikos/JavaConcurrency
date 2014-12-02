package basic;

public class Client {
    private TaskRunner taskRunner;

    Client (TaskRunner taskRunner) {
        this.taskRunner = taskRunner;
    }

    public void generate() {
        int i = 0;
        while (++i < 10) {
            taskRunner.run(new TaskImpl<>(), i);
        }
    }
}
