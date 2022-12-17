package src.objects;

import gui.Window;

public class Obstacle extends GameObjectBase {
    public Obstacle(int x, int y) {
        super(x, y);
    }

    public void drawObstacle(Window window) {
        super.drawGeometry(window);
    }

    @Override
    public boolean intersects(GameObjectBase otherObject){
        return x - width/2 < otherObject.x + otherObject.width/2
                && x + width/2 > otherObject.x - otherObject.width/2
                && y - height/2 < otherObject.y + otherObject.height/2
                && y + height/2 > otherObject.y - otherObject.height/2;
    }
}
