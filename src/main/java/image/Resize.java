package image;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;

public class Resize implements Runnable {
    private static int counter = 1;
    private final int id = counter++;
    private final ResizeCallback callback;
    private final String fileName;
    private final String newName;
    private final int width;

    public Resize(String fileName, String newName, int width, ResizeCallback callback) {
        this.fileName = fileName;
        this.newName = newName;
        this.width = width;
        this.callback = callback;
    }

    @Override
    public void run() {
        System.out.println(getTaskId() + "(" + fileName + ") started");
        ConvertCmd cmd = new ConvertCmd();
        IMOperation operation = new IMOperation();
        operation.addImage(fileName);
        operation.liquidRescale(width);
        operation.addImage();
        try {
            cmd.run(operation, newName);
            System.out.println(getTaskId() + "(" + newName + ") created");
            callback.call(newName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTaskId() {
        return "Task-" + id;
    }
}
