package basic;

public class Main {

    public static void main(String[] args) {
        Client client = new Client(new TaskRunnerImpl());
        client.generate();
    }
}
