package src.objects;

import gui.Window;

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
        this(x, y, 40, 40);
    }

    public void drawGeometry(Window window) {
        window.setColor(0, 0, 0);
        window.fillRect(x, y, width, height);
    }

    public void drawImage(Window window, String path) {
        window.drawImageCentered(path, x, y);
    }

    public boolean intersects(GameObjectBase otherObject) {
        if (otherObject instanceof Obstacle) {
            boolean upperCollision = false;
            boolean lowerCollision = false;
            boolean leftCollision = false;
            boolean rightCollision = false;

            int playerLeft = this.x - width / 2;
            int playerRight = this.x + width / 2;
            int playerTop = this.y - height / 2;
            int playerBottom = this.x + height / 2;

            int obstacleLeft = otherObject.x + width;
            int obstacleRight = otherObject.x;
            int obstacleTop = otherObject.y;
            int obstacleBottom = otherObject.y + height;

            if ((playerTop < obstacleTop && obstacleTop < playerBottom) || (playerTop < obstacleBottom && obstacleBottom < playerBottom)) {
                leftCollision = (playerLeft < obstacleRight);
            }

            if ((playerTop < obstacleTop && obstacleTop < playerBottom) || (playerTop < obstacleBottom && obstacleBottom < playerBottom)) {
                rightCollision = (playerRight > obstacleLeft);
            }

            //TODO fix collision with walls
            return upperCollision
                    || lowerCollision
                    || leftCollision
                    || rightCollision;
        }

        return x - width / 2 < otherObject.x + otherObject.width / 2
                && x + width / 2 > otherObject.x - otherObject.width / 2
                && y - height / 2 < otherObject.y + otherObject.height / 2
                && y + height / 2 > otherObject.y - otherObject.height / 2;
    }
}
