package src.gui;

import gui.Window;

public class Finish extends GameObjectBase {

    public Finish(int x, int y) {
        super(x, y);
    }

    public void drawFinish(Window window) {
        super.drawImage(window, "EN2_EatNRun/resources/images/finish.png");
    }
}
