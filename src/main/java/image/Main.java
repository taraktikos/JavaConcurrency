package image;


import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] names = new String[]{
                new File("images/1545769.jpg").getAbsolutePath(),
                new File("images/1605647.jpg").getAbsolutePath(),
                new File("images/1924254.jpg").getAbsolutePath(),
                new File("images/turtle_travelling_underwater.jpg").getAbsolutePath()
        };

        Map<String, ArrayList<String>> resizedImages = new HashMap<>();

        for (String originalName: names) {
            ArrayList<String> list = new ArrayList<>();
            list.add(originalName);
            resizedImages.put(originalName, list);
            new Resize(originalName, 800, 600, new ResizeCallback() {
                @Override
                public void call(String file) {
                    System.out.println("Callback " + file);
                    resizedImages.get(originalName).add(file);
                }
            }).run();
            new Animate(resizedImages.get(originalName)).run();
        }

        System.out.println("Done");
    }
}
