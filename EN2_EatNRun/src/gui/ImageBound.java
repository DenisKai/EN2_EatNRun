package src.gui;

public class ImageBound extends ObjectBound {
    public ImageBound(int x, int y, int width, int height) {
        super(x - width / 2, y - height / 2, width, height);
    }
}
