package it.unibo.mvc;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 */
public final class SimpleGUI {
    private static final String TITLE = "92 Simple MVC I/O";
    private static final int PROPORTION = 5;
    private static final int TEXT_AREA_ROWS = 40;
    private static final int TEXT_AREA_COLUMNS = 80;
    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller = new Controller();

    /**
     * Default constructor to create a new instance of SimpleGUI.
     */
    public SimpleGUI() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        final JTextArea textArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
        final JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        canvas.add(textAreaScrollPane, BorderLayout.NORTH);
        final JButton saveButton = new JButton("Save");
        canvas.add(saveButton, BorderLayout.SOUTH);
        // init frame
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Handlers
         */
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    controller.writeDataToFile(textArea.getText());
                    JOptionPane.showMessageDialog(frame,
                            "File " + controller.getCurrentFile() + " salvato con successo ^_^");
                } catch (final IOException exception) {
                    JOptionPane.showMessageDialog(frame, exception, "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
    }

    // is just an exercise SimpleGUIWithFileChooser and SimpleGUI are similar -
    // CPD-OFF
    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very method is
         * enough for a single screen setup. In case of multiple monitors, the
         * primary is selected. In order to deal coherently with multimonitor
         * setups, other facilities exist (see the Java documentation about this
         * issue). It is MUCH better than manually specify the size of a window
         * in pixel: it takes into account the current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * OK, ready to push the frame onscreen
         */
        frame.setVisible(true);
        frame.pack();
    }

    /**
     * Main entry point for the launcher.
     * 
     * @param args the command line parameters
     */
    public static void main(final String... args) {
        new SimpleGUI().display();
    }
    // resume CPD analysis - CPD-ON
}
