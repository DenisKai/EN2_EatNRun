package src.gui;

import gui.Window;
import src.logic.EatNRunGame;

public abstract class GameObjectBase {
    protected int x;
    protected int y;
    protected final int width;
    protected final int height;

    public GameObjectBase(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public GameObjectBase(int x, int y) {
        this(x, y, EatNRunGame.GAMEOBJECT_WIDTH, EatNRunGame.GAMEOBJECT_HEIGHT);
    }

    public void drawGeometry(Window window) {
        window.setColor(0, 0, 0);
        window.fillRect(x, y, width, height);
    }

    public void drawImage(Window window, String path) {
        window.drawImageCentered(path, x, y);
    }

    public boolean intersects(GameObjectBase otherObject) {
        return x - width / 2 < otherObject.x + otherObject.width / 2
                && x + width / 2 > otherObject.x - otherObject.width / 2
                && y - height / 2 < otherObject.y + otherObject.height / 2
                && y + height / 2 > otherObject.y - otherObject.height / 2;
    }
}
