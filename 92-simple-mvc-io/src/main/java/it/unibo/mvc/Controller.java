package it.unibo.mvc;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public final class Controller {
    private static final String DEFAULT_FILE_NAME = "output.txt";
    private static final String DEFAULT_FILE_PATH = System.getProperty("user.home")
            + System.getProperty("file.separator")
            + DEFAULT_FILE_NAME;
    private String currentFilePath;

    public Controller() {
        this.currentFilePath = DEFAULT_FILE_PATH;
    }

    public void setCurrentFile(final String path) {
        this.currentFilePath = path;
    }

    public String getCurrentFile() {
        return this.currentFilePath;
    }

    public void writeDataToFile(final String data) throws IOException {
        try (PrintStream printStream = new PrintStream(this.currentFilePath, StandardCharsets.UTF_8)) {
            printStream.println(data);
        }
    }
}
