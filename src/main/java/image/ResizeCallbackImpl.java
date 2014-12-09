package image;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

public class ResizeCallbackImpl implements ResizeCallback {

    private ExecutorService service;
    private List<String> images;
    private AtomicInteger countImages;

    public ResizeCallbackImpl(ExecutorService service, List<String> images) {
        this.service = service;
        this.images = images;
        this.countImages = new AtomicInteger(images.size());
    }

    @Override
    public void call(String file) {
        System.out.println("Callback " + file);
        if (countImages.decrementAndGet() == 0) {
            service.submit(new Animate(images));
        }
    }
}
