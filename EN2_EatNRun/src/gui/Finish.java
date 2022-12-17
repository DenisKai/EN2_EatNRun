package src.gui;

import gui.Window;

public class Finish extends GameObjectBase implements ImageInterface {

    public Finish(int x, int y) {
        super(x, y);
    }

    public void drawFinish(Window window) {
        super.drawImage(window, BASE_URL + "finish.png");
    }
}
