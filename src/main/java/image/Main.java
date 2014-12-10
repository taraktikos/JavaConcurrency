package image;


import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        String[] names = new String[]{
                new File("images/1545769.jpg").getAbsolutePath(),
                new File("images/1605647.jpg").getAbsolutePath(),
                new File("images/1924254.jpg").getAbsolutePath(),
                new File("images/123423.jpg").getAbsolutePath()
        };
        int maxWidth = 600;
        int minWidth = 100;
        int step = 50;
        ExecutorService service = Executors.newFixedThreadPool(5);
        Callback lastAnimateCallback = new Callback() {
            private AtomicInteger countAnimations = new AtomicInteger(names.length);
            @Override
            public void call(String file) {
                if (countAnimations.decrementAndGet() == 0) {
                    service.shutdown();
                    System.out.println("Done");
                }
            }
        };
        for (String originalName: names) {
            NavigableMap<Integer, String> imageNames = new TreeMap<>();
            imageNames.put(maxWidth, originalName);
            for (int width = maxWidth; width > minWidth; width-=step) {
                File file = new File(originalName);
                int pointPosition = file.getName().lastIndexOf(".");
                String ext = file.getName().substring(pointPosition);
                String oldName = file.getName().substring(0, pointPosition);
                String newFileName = file.getParentFile() + "/generated/" + oldName + width + ext;
                imageNames.put(width, newFileName);
            }
            Callback callback = new ResizeCallback(service, new ArrayList<>(imageNames.descendingMap().values()), lastAnimateCallback);
            for(Map.Entry<Integer, String> entry : imageNames.entrySet()) {
                Integer width = entry.getKey();
                String newName = entry.getValue();
                service.submit(new Resize(originalName, newName, width, callback));
            }
        }
    }
}
