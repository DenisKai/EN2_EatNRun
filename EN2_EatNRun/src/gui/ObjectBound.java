package src.gui;

public class ObjectBound {
    protected int leftBoundary;
    protected int rightBoundary;
    protected int topBoundary;
    protected int bottomBoundary;

    public ObjectBound(int x, int y, int width, int height) {
        this.leftBoundary = x;
        this.rightBoundary = x + width;
        this.topBoundary = y;
        this.bottomBoundary = y + height;
    }
}
