package basic;

public class TaskImpl<V> implements Task<V> {

    @Override
    public void run(V value) {
        System.out.println(value);
    }
}
