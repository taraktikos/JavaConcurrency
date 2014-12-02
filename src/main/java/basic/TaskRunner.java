package basic;

public interface TaskRunner {
    <V> boolean run(Task<V> task, V value);
}
