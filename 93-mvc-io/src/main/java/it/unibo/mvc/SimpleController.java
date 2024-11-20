package it.unibo.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A simple implementation of the Controller interface.
 */
public final class SimpleController implements Controller {
    private final List<String> history;
    private String currenItem;

    /**
     * Default constructor to create a new instance of SimpleController.
     */
    public SimpleController() {
        this.currenItem = null;
        this.history = new ArrayList<>();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextText(final String text) {
        if (Objects.isNull(text) || text.isEmpty() || text.isBlank()) {
            throw new IllegalArgumentException("Parameter text can't be null, empty or blank !!!");
        }

        this.currenItem = text;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNextText() {
        return this.currenItem;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getHistory() {
        return Collections.unmodifiableList(this.history);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printToStdout() {
        if (Objects.isNull(this.currenItem) || this.currenItem.isEmpty() || this.currenItem.isBlank()) {
            throw new IllegalStateException("There isn't any data to print, please use setNextText() to set it !!!");
        }

        System.out.println(currenItem); // NOPMD - allowed as this is just an exercise
        this.history.add(currenItem);
        this.currenItem = null;
    }
}
