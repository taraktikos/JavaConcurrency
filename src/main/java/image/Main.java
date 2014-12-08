package image;


public class Main {

    public static void main(String[] args) {
//        ConvertCmd cmd = new ConvertCmd();
//        IMOperation operation = new IMOperation();
//        Info imageInfo;
//        try {
//            imageInfo = new Info("/home/taras/Pictures/test.png", true);
//            operation.addImage("/home/taras/Pictures/test.png");
//            operation.liquidRescale(imageInfo.getImageWidth() - 100, imageInfo.getImageHeight());
//            operation.addImage();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        new Resize("/home/taras/Pictures/test.png", 800, 600, new ResizeCallback() {
            @Override
            public void call(String file) {
                System.out.println("Callback " + file);
            }
        }).run();

        /*operation.addImage("/home/taras/Pictures/resized_picture.png");
        operation.delay(100).loop();
        operation.addImage("/home/taras/Pictures/animation.gif");*/

        //convert -delay 10 -loop 0 inputfiles*.png animaion.gif
//        IMOperation createAnimation = new IMOperation();
//        createAnimation.delay(50);
//        createAnimation.loop(0);
//        createAnimation.addImage("/home/taras/Pictures/test.png");
//        createAnimation.addImage("/home/taras/Pictures/resized_picture.png");
//        createAnimation.addImage();
//        try {
//            cmd.run(operation, "/home/taras/Pictures/resized_picture.png");
//            cmd.run(createAnimation, "/home/taras/Pictures/animaion.gif");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println("Done");
    }
}
