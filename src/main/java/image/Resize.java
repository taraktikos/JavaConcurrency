package image;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import java.io.File;

public class Resize implements Runnable {
    private static int counter = 1;
    private final int id = counter++;
    private final ResizeCallback callback;
    private final String fileName;
    private final int width;

    public Resize(String fileName, int width, ResizeCallback callback) {
        this.fileName = fileName;
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
        File file = new File(fileName);
        int pointPosition = file.getName().lastIndexOf(".");
        String ext = file.getName().substring(pointPosition);
        String oldName = file.getName().substring(0, pointPosition);
        String newFileName = file.getParentFile() + "/generated/" + oldName + width + ext;
        try {
            cmd.run(operation, newFileName);
            System.out.println(getTaskId() + "(" + newFileName + ") created");
            callback.call(newFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTaskId() {
        return "Task-" + id;
    }
}
