package src.gui;

import gui.Window;
import src.logic.Direction;
import src.logic.EatNRunGame;

public class Player extends MovableObject implements ImageInterface {
    private final int spawnX;
    private final int spawnY;
    private int score;
    private int lives;
    private final int movementSpeed = 5;

    public Player(int x, int y) {
        super(x + 20, y + 20, 20, 30);
        this.spawnX = x;
        this.spawnY = y;
        this.lives = 5;
    }

    public Player(int x, int y, int lives, int score) {
        this(x, y);
        this.lives = lives;
        this.score = score;
    }

    public void drawPlayer(Window window) {
        super.drawImage(window, BASE_URL + "hero.png");
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
        final int currentX = this.x;
        final int currentY = this.y;

        if (direction.equals(Direction.UP)) {
            move(this.x, this.y - movementSpeed);
        }
        if (direction.equals(Direction.LEFT)) {
            move(this.x - movementSpeed, this.y);
        }
        if (direction.equals(Direction.DOWN)) {
            move(this.x, this.y + movementSpeed);
        }
        if (direction.equals(Direction.RIGHT)) {
            move(this.x + movementSpeed, this.y);
        }

        if (checkCollision(obstacles)) {
            this.x = currentX;
            this.y = currentY;
        }
    }

    private void resetPlayer() {
        this.x = this.spawnX + EatNRunGame.GAMEOBJECT_WIDTH / 2;
        this.y = this.spawnY + EatNRunGame.GAMEOBJECT_HEIGHT / 2;
    }
}
