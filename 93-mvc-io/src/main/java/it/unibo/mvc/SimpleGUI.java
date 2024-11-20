package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {
    private static final int PROPORTION = 5;
    private static final String FRAME_TITLE = "93 MVC I/O";
    private final JFrame frame = new JFrame(FRAME_TITLE);
    private static final int TEXT_AREA_ROWS = 40;
    private static final int TEXT_AREA_COLUMNS = 80;
    private final Controller controller;

    /**
     * Default constructor to create a new instance of SimpleGUI.
     */
    public SimpleGUI() {
        this.controller = new SimpleController();
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        final JTextField currentTextField = new JTextField();
        mainPanel.add(currentTextField, BorderLayout.NORTH);
        final JTextArea historyTextArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
        historyTextArea.setEditable(false);
        final JScrollPane historyScrollPane = new JScrollPane(historyTextArea);
        mainPanel.add(historyScrollPane, BorderLayout.CENTER);
        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        final JButton printButton = new JButton("Print");
        buttonsPanel.add(printButton);
        final JButton historyButton = new JButton("Show history");
        buttonsPanel.add(historyButton);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);
        // handlers
        currentTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(final KeyEvent e) {
            }

            @Override
            public void keyPressed(final KeyEvent e) {
            }

            @Override
            public void keyReleased(final KeyEvent e) {
                controller.setNextText(currentTextField.getText());
            }
        });
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.printToStdout();
                    currentTextField.setText("");
                } catch (IllegalStateException exception) {
                    JOptionPane.showMessageDialog(frame, exception.getMessage(), "Oops something went wrong :(",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final StringBuilder history = new StringBuilder();
                for (final String item : controller.getHistory()) {
                    history.append(item).append(System.getProperty("line.separator"));
                }
                historyTextArea.setText(history.toString());
            }
        });
        // init frame
        this.frame.setContentPane(mainPanel);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Main entry point for the launcher.
     * 
     * @param args the command line parameters
     */
    public static void main(final String[] args) {
        new SimpleGUI().display();
    }
}
