package pt.tabem.aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputHelper {

    public static List<String> loadLines(String input) throws IOException {
        return Files.readAllLines(Paths.get("./inputs/" + input));
    }

    public static String loadAsString(String input) throws IOException {
        return Files.readString(Paths.get("./inputs/" + input));
    }
}
