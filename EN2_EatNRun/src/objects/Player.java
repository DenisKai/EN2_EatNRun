package src.objects;

import gui.Window;

public class Player extends MovableObject {
    private int score;
    private int lives;

    public Player(int x, int y, int width, int height) {
        super(x, y, width, height);
        this.lives = 5;
    }
    public void drawPlayer(Window window) {
        super.drawImage(window, "EN2_EatNRun/resources/images/hero.png");
    }

    public int getScore() {
        return this.score;
    }

    public int addScore() {
        return score++;
    }

    public int loseLife() {
        return this.lives--;
    }

    public void moveUp() {
        super.move(x, y - 5);
    }

    public void moveRight() {
        super.move(x + 5, y);
    }

    public void moveDown() {
        super.move(x, y + 5);
    }

    public void moveLeft() {
        super.move(x - 5, y);
    }
}
