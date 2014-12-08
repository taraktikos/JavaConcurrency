package image;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        list.add("/home/taras/Pictures/test.png");
        new Resize("/home/taras/Pictures/test.png", 800, 600, new ResizeCallback() {
            @Override
            public void call(String file) {
                System.out.println("Callback " + file);
                list.add(file);
            }
        }).run();

        new Animate(list).run();

        System.out.println("Done");
    }
}
