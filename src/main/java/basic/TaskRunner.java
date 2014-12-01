package basic;

public interface TaskRunner {
    <R, V> R run(Task<V> task, V value);
}
