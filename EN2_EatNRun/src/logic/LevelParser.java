package src.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LevelParser {
    public static String[] loadTextFile(int level) {
        try {
            return Files.readAllLines(Paths.get("EN2_EatNRun", "resources", "maps", level + ".txt")).toArray(new String[]{});
        } catch (IOException iox) {
            throw new RuntimeException(iox);
        }
    }

    public static int getNumberOfLevels() {
        try {
            return (int) Files.list(Paths.get("EN2_EatNRun", "resources", "maps"))
                    .filter(p -> p.toFile().getName().endsWith(".txt"))
                    .count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
