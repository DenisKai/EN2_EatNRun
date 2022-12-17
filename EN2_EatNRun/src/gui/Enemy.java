package src.gui;

import gui.Window;

public class Enemy extends MovableObject implements ImageInterface {
    private int velocityX;
    private int velocityY;

    public Enemy(int x, int y, int velocityX, int velocityY) {
        super(x, y);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void drawEnemy(Window window) {
        drawImage(window, BASE_URL + "monster.png");
    }

    public void move(Obstacle[] obstacles) {
        final int currentX = x;
        final int currentY = y;

        move(x + velocityX, y + velocityY);

        if (checkCollision(obstacles)) {
            x = currentX;
            y = currentY;
            velocityX *= -1;
            velocityY *= -1;
        }
    }

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }
}
