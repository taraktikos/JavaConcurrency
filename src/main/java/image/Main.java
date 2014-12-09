package image;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        String[] names = new String[]{
                new File("images/1545769.jpg").getAbsolutePath(),
                new File("images/1605647.jpg").getAbsolutePath(),
                new File("images/1924254.jpg").getAbsolutePath(),
                new File("images/123423.jpg").getAbsolutePath()
        };
        Map<String, ArrayList<String>> resizedImages = new HashMap<>();
        int maxWidth = 600;
        int minWidth = 100;
        int step = 50;
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (String originalName: names) {
            ArrayList<String> list = new ArrayList<>();
            list.add(originalName);
            resizedImages.put(originalName, list);
            for (int width = maxWidth; width > minWidth; width-=step) {
                service.submit(new Resize(originalName, width, new ResizeCallback() {
                    @Override
                    public void call(String file) {
                        System.out.println("Callback " + file);
                        ArrayList<String> images = resizedImages.get(originalName);
                        images.add(file);
                        System.out.println("size" + images.size());
                        if (images.size() == (maxWidth - minWidth) / step + 1) {
                            service.submit(new Animate(images));
                        }
                    }
                }));
            }
            //new Animate(resizedImages.get(originalName)).run();
        }
        System.out.println("Done");
    }
}
