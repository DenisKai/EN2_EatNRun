package src.gui;

import gui.Window;

public class Cake extends GameObjectBase implements ImageInterface {
    public Cake(int x, int y) {
        super(x, y);
    }

    public void drawCake(Window window) {
        drawImage(window, BASE_URL + "cake.png");
    }
}
