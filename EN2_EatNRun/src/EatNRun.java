package src;

import gui.Window;
import src.logic.EatNRunGame;

public class EatNRun {
    private static int WIDTH = 800;
    private static int HEIGHT = 600;

    public static void main(String[] args) {

        EatNRunGame game = new EatNRunGame(WIDTH, HEIGHT);

        Window window = new Window("Eat'n'Run", WIDTH, HEIGHT);
        window.open();

        // Game loop
        while (window.isOpen()) {
            // Prozessiert Benutzereingaben
            game.handleEvents(window);

            // Prozessiert einen einzelnen Zeitschritt
            game.step();

            // Zeichnet den Spielzustand
            game.drawGame(window);

            window.refreshAndClear(20);
        }
    }
}
