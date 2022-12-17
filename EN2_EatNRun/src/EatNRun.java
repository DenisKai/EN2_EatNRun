package src;

import gui.Window;
import src.logic.EatNRunGame;
import src.logic.GameStatus;

public class EatNRun {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public static void main(String[] args) {

        EatNRunGame game = new EatNRunGame(WIDTH, HEIGHT);

        Window window = new Window("Eat'n'Run", WIDTH, HEIGHT);
        window.open();

        while (window.isOpen()) {
            if (game.getGameStatus().equals(GameStatus.ONGOING)) {
                game.handlePlayerEvents(window);

                game.handleGameEvents(window);

                game.drawGame(window);

                window.refreshAndClear(20);
            } else {
                window.refreshAndClear();
                window.setColor(0, 0, 0);

                if (game.getGameStatus().equals(GameStatus.WON)) {
                    displayMessage(window, "You Win!");
                } else {
                    displayMessage(window, "Game Over!");
                }
            }
        }
    }

    private static void displayMessage(Window window, String message) {
        window.setFontSize(40);
        window.drawStringCentered(message, window.getWidth() / 2, window.getHeight() / 2);
    }
}
