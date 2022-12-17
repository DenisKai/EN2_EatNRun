package src.logic;

import gui.Window;
import src.gui.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EatNRunGame {
    private final int width;
    private final int height;

    public static final int GAMEOBJECT_WIDTH = 40;
    public static final int GAMEOBJECT_HEIGHT = 40;

    //TODO better handling of drawable figures
    private Player player;
    private Enemy[] enemies;
    private Obstacle[] obstacles;
    private Cake[] cakes;
    private Finish finish;
    private int currentLevel;

    private GameStatus gameStatus;

    public EatNRunGame(int width, int height) {
        this.width = width;
        this.height = height;
        this.currentLevel = 1;
        this.gameStatus = GameStatus.ONGOING;

        // Generate initial level
        parseLevel();
    }

    public void drawGame(Window window) {
        player.drawPlayer(window);
        finish.drawFinish(window);

        for (Cake cake : cakes) {
            if (cake != null) {
                cake.drawCake(window);
            }
        }

        for (Obstacle obstacle : obstacles) {
            obstacle.drawObstacle(window);
        }

        for (Enemy enemy : enemies) {
            enemy.drawEnemy(window);
        }

        window.setColor(255, 255, 255);
        window.setFontSize(24);
        window.drawStringCentered(String.format("Cakes: %s Lifes: %s Level: %s", player.getScore(), player.getLives(), this.currentLevel), width / 2, height - 10);
    }

    public void handleGameEvents(Window window) {
        if (player.intersects(finish)) {
            changeLevel();
        }

        for (Enemy enemy : enemies) {
            enemy.move(obstacles);
        }

        for (Enemy enemy : enemies) {
            if (player.intersects(enemy)) {
                player.loseLife();
                if (player.getLives() < 1) {
                    gameStatus = GameStatus.LOST;
                }
            }
        }

        for (int i = 0; i < cakes.length; i++) {
            if (cakes[i] != null && player.intersects(cakes[i])) {
                player.addScore();
                cakes[i] = null;
            }
        }
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void handlePlayerEvents(Window window) {
        if (window.isKeyPressed("up")) {
            player.move(Direction.UP, obstacles);
        }
        if (window.isKeyPressed("left")) {
            player.move(Direction.LEFT, obstacles);
        }
        if (window.isKeyPressed("down")) {
            player.move(Direction.DOWN, obstacles);
        }
        if (window.isKeyPressed("right")) {
            player.move(Direction.RIGHT, obstacles);
        }
    }

    private void parseLevel() {
        String[] abstractLevel = loadTextFile(currentLevel);
        clearGame();
        setupGame(abstractLevel);

        for (int y = 0; y < abstractLevel.length; y++) {
            char[] layer = abstractLevel[y].toCharArray();

            for (int x = 0; x < layer.length; x++) {
                final int positionX = GAMEOBJECT_WIDTH * x;
                final int positionY = GAMEOBJECT_HEIGHT * y;

                switch (layer[x]) {
                    case '#':
                        addToArray(new Obstacle(positionX, positionY));
                        break;

                    case 'P':
                        if (player != null) {
                            this.player = new Player(positionX, positionY, this.player.getLives(), this.player.getScore());
                        } else {
                            this.player = new Player(positionX, positionY);
                        }
                        break;

                    case 'F':
                        this.finish = new Finish(positionX + GAMEOBJECT_WIDTH / 2, positionY + GAMEOBJECT_HEIGHT / 2);
                        break;

                    case 'C':
                        addToArray(new Cake(positionX + GAMEOBJECT_WIDTH / 2, positionY + GAMEOBJECT_HEIGHT / 2));
                        break;

                    case 'N':
                        addToArray(new Enemy(positionX + GAMEOBJECT_WIDTH / 2, positionY + GAMEOBJECT_HEIGHT / 2, 0, -5));
                        break;

                    case 'E':
                        addToArray(new Enemy(positionX + GAMEOBJECT_WIDTH / 2, positionY + GAMEOBJECT_HEIGHT / 2, 5, 0));
                        break;

                    case 'S':
                        addToArray(new Enemy(positionX + GAMEOBJECT_WIDTH / 2, positionY + GAMEOBJECT_HEIGHT / 2, 0, 5));
                        break;

                    case 'W':
                        addToArray(new Enemy(positionX + GAMEOBJECT_WIDTH / 2, positionY + GAMEOBJECT_HEIGHT / 2, -5, 0));
                        break;

                    default:
                        break;
                }
            }
        }
    }

    private void setupGame(String[] abstractLevel) {
        int countObstacles = 0;
        int countEnemies = 0;
        int countCakes = 0;

        for (String row : abstractLevel) {
            char[] column = row.toCharArray();
            for (char object : column) {
                if (object == '#') {
                    countObstacles++;
                }

                if (object == 'N' || object == 'E' || object == 'S' || object == 'W') {
                    countEnemies++;
                }

                if (object == 'C') {
                    countCakes++;
                }
            }
        }

        this.obstacles = new Obstacle[countObstacles];
        this.enemies = new Enemy[countEnemies];
        this.cakes = new Cake[countCakes];
    }

    private void addToArray(GameObjectBase gameObject) {
        if (gameObject instanceof Cake) {
            for (int i = 0; i < this.cakes.length; i++) {
                if (cakes[i] == null) {
                    cakes[i] = (Cake) gameObject;
                    break;
                }
            }
            return;
        }

        if (gameObject instanceof Obstacle) {
            for (int i = 0; i < this.obstacles.length; i++) {
                if (obstacles[i] == null) {
                    obstacles[i] = (Obstacle) gameObject;
                    break;
                }
            }
            return;
        }

        if (gameObject instanceof Enemy) {
            for (int i = 0; i < this.enemies.length; i++) {
                if (enemies[i] == null) {
                    enemies[i] = (Enemy) gameObject;
                    break;
                }
            }
            return;
        }
    }

    private void changeLevel() {
        if (currentLevel + 1 > LevelParser.getNumberOfLevels()) {
            this.gameStatus = GameStatus.WON;
        } else {
            currentLevel++;
            parseLevel();
        }
    }

    private static String[] loadTextFile(int level) {
        try {
            return Files.readAllLines(Paths.get("EN2_EatNRun", "resources", "maps", level + ".txt")).toArray(new String[]{});
        } catch (IOException iox) {
            throw new RuntimeException(iox);
        }
    }

    private void clearGame() {
        this.obstacles = null;
        this.enemies = null;
        this.finish = null;
    }
}
