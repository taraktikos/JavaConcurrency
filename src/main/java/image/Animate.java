package image;

import org.im4java.core.ConvertCmd;
import org.im4java.core.IMOperation;
import java.io.File;
import java.util.ArrayList;

public class Animate implements Runnable {
    private static int counter = 1;
    private final int id = counter++;
    private final ArrayList<String> files;

    public Animate(ArrayList<String> files) {
        this.files = files;
    }

    @Override
    public void run() {
        System.out.println(getTaskId() + "Animate started");
        ConvertCmd cmd = new ConvertCmd();
        IMOperation createAnimation = new IMOperation();
        createAnimation.delay(50);
        createAnimation.loop(0);
        for (String file: files) {
            createAnimation.addImage(file);
        }
        createAnimation.addImage();

        File file = new File(files.get(0));
        int pointPosition = file.getName().lastIndexOf(".");
        String ext = ".gif";
        String oldName = file.getName().substring(0, pointPosition);

        String newFileName = file.getParentFile() + "/generated/" + oldName + ext;
        try {
            cmd.run(createAnimation, newFileName);
            System.out.println(getTaskId() + "Animate end " + newFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTaskId() {
        return "Task-" + id;
    }
}
