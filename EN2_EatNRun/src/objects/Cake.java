package src.objects;

import gui.Window;

public class Cake extends GameObjectBase {
    public Cake(int x, int y) {
        super(x, y);
    }

    public void drawCake(Window window) {
        drawImage(window, "EN2_EatNRun/resources/images/cake.png");
    }
}
