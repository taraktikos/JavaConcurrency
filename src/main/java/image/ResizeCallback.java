package image;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class ResizeCallback implements Callback {

    private ExecutorService service;
    private List<String> images;
    private AtomicInteger countImages;
    private Callback callback;

    public ResizeCallback(ExecutorService service, List<String> images, Callback callback) {
        this.service = service;
        this.images = images;
        this.countImages = new AtomicInteger(images.size());
        this.callback = callback;
    }

    @Override
    public void call(String file) {
        System.out.println("Callback " + file);
        if (countImages.decrementAndGet() == 0) {
            service.submit(new Animate(images, callback));
        }
    }
}
