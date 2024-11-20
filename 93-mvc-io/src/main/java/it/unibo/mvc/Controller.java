package it.unibo.mvc;

import java.util.List;

/**
 * Interface for simple controller responsible of I/O access.
 */
public interface Controller {
    /**
     * Create a new element to be printed to standard output.
     * 
     * @param text data to print
     * @throws IllegalArgumentException if text is null, empty or blank
     */
    void setNextText(String text);

    /**
     * Get element that will be printed to standard output.
     * 
     * @return String data to print
     */
    String getNextText();

    /**
     * Get the list of elements that have been printed to standard output.
     * 
     * @return List<String> all the elements that have already been printed
     */
    List<String> getHistory();

    /**
     * Print the current element to standard output.
     * 
     * @throws IllegalStateException if the current element is not set
     */
    void printToStdout();
}
