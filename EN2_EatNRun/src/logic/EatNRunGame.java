package src.logic;

import src.objects.Enemy;
import src.objects.Obstacle;
import src.objects.Player;
import gui.Window;

public class EatNRunGame {
    private int width;
    private int height;

    //TODO better handling of drawable figures
    private Player player;
    private Enemy[] enemies;
    private Obstacle[] obstacles;

    public EatNRunGame(int width, int height) {
        this.width = width;
        this.height = height;

        this.player = new Player(150, 150, 25,25);
    }

    public void drawGame(Window window) {
        this.player.drawPlayer(window);
    }

    public void step() {
    }

    public void handleEvents(Window window) {
        if (window.isKeyPressed("w")) {
            player.moveUp();
        }
        if (window.isKeyPressed("a")) {
            player.moveLeft();
        }
        if (window.isKeyPressed("s")) {
            player.moveDown();
        }
        if (window.isKeyPressed("d")) {
            player.moveRight();
        }

        player.drawPlayer(window);
    }
}
