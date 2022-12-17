package src.gui;

public abstract class MovableObject extends GameObjectBase {

    public MovableObject(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    public MovableObject(int x, int y) {
        super(x, y);
    }

    public void move(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    public boolean checkCollision(Obstacle[] obstacles) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.intersects(this)) {
                return true;
            }
        }

        return false;
    }
}
