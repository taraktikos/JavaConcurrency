package image;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] names = new String[]{
                "/home/taras/Pictures/test.png"
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
