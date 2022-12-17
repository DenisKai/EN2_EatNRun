package src.objects;

import gui.Window;

public class Enemy extends MovableObject {
    private int velocityX;
    private int velocityY;

    public Enemy(int x, int y, int velocityX, int velocityY) {
        super(x, y);
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void drawEnemy(Window window) {
        drawImage(window, "EN2_EatNRun/resources/images/monster.png");
    }

    public void move(Obstacle[] obstacles) {
        super.move(x + velocityX, y + velocityY);

        for (Obstacle obstacle: obstacles) {
            if (this.intersects(obstacle)) {
                velocityY *= -1;
                velocityX *= -1;
            }
        }
    }
}
