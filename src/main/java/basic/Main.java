package basic;

public class Main {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Test");
            }
        });

        t.run();

        Client client = new Client(new TaskRunnerImpl());
        client.generate();
    }
}
