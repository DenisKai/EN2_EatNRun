package src.objects;

import gui.Window;

public abstract class GameObjectBase {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    public GameObjectBase(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void drawGeometry(Window window) {
        window.setColor(255,0,0);
        window.fillRect(x, y, width, height);
    }

    public void drawImage(Window window, String path) {
        window.drawImageCentered(path, x, y);
    }

    public boolean intersects(GameObjectBase otherObject){
        return x - width/2  < otherObject.x + otherObject.width/2
                && x + width/2  > otherObject.x - otherObject.width/2
                && y - height/2 < otherObject.y + otherObject.height/2
                && y + height/2 > otherObject.y - otherObject.height/2;
    }
}
