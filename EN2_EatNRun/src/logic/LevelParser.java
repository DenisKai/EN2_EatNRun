package src.logic;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LevelParser {
    private static String[] loadTextFile(int level) {
        try {
            return Files.readAllLines(Paths.get("resources", "maps", level + ".txt")).toArray(new String[]{});
        } catch(IOException iox) {
            throw new RuntimeException(iox);
        }
    }

    // TODO implement level load
}
