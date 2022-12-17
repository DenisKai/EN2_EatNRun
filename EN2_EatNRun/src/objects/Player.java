package src.objects;

import gui.Window;
import src.logic.Direction;

public class Player extends MovableObject {
    private final int spawnX;
    private final int spawnY;
    private int score;
    private int lives;
    private final int MOVEMENT_SPEED = 5;

    public Player(int x, int y) {
        super(x + 20, y + 20, 20, 30);
        this.spawnX = x;
        this.spawnY = y;
        this.lives = 5;
    }

    public Player(int x, int y, int score) {
        this(x, y);
        this.score = score;
    }

    public void drawPlayer(Window window) {
        super.drawImage(window, "EN2_EatNRun/resources/images/hero.png");
    }

    public int addScore() {
        return score++;
    }

    public int getScore() {
        return this.score;
    }

    public int getLives() {
        return this.lives;
    }
    public void loseLife() {
        resetPlayer();
        this.lives--;
    }

    public void move(Direction direction, Obstacle[] obstacles) {
        final int CURRENT_X = this.x;
        final int CURRENT_Y = this.y;

        if (direction.equals(Direction.UP)) {
            this.y = y - MOVEMENT_SPEED;
        }
        if (direction.equals(Direction.LEFT)) {
            this.x = x - MOVEMENT_SPEED;
        }
        if (direction.equals(Direction.DOWN)) {
            this.y = y + MOVEMENT_SPEED;
        }
        if (direction.equals(Direction.RIGHT)) {
            this.x = x + MOVEMENT_SPEED;
        }

        if (!checkCollision(this, obstacles)) {
            super.move(this.x, this.y);
        } else {
            this.x = CURRENT_X;
            this.y = CURRENT_Y;
        }
    }

    private boolean checkCollision(Player player, Obstacle[] obstacles) {
        boolean collision = false;

        for (Obstacle obstacle: obstacles) {
            collision = player.intersects(obstacle);
        }

        return collision;
    }

    private void resetPlayer() {
        this.x = this.spawnX + width / 2;
        this.y = this.spawnY + height / 2;
    }
}
