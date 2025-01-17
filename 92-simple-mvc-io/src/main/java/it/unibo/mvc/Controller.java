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

    /**
     * Default constructor to create a new instance of Controller.
     */
    public Controller() {
        this.currentFilePath = DEFAULT_FILE_PATH;
    }

    /**
     * Set the current file path.
     * 
     * @param path the file path of the current file
     */
    public void setCurrentFile(final String path) {
        this.currentFilePath = path;
    }

    /**
     * Return the file path of the current selected file.
     * 
     * @return String the file path.
     */
    public String getCurrentFile() {
        return this.currentFilePath;
    }

    /**
     * Write input data to a file.
     * 
     * @param data the text to write on the current file
     * @throws IOException if an I/O error occurs while opening or creating the file
     */
    public void writeDataToFile(final String data) throws IOException {
        try (PrintStream printStream = new PrintStream(this.currentFilePath, StandardCharsets.UTF_8)) {
            printStream.println(data);
        }
    }
}
