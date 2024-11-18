package it.unibo.mvc;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private static final String TITLE = "92 Simple MVC I/O";
    private static final int PROPORTION = 5;
    private static final int TEXT_AREA_ROWS = 40;
    private static final int TEXT_AREA_COLUMNS = 80;
    private final JFrame frame = new JFrame(TITLE);
    private final Controller controller = new Controller();

    public SimpleGUIWithFileChooser() {
        final JPanel canvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        // file chooser
        final JPanel fileChooserPanel = new JPanel();
        fileChooserPanel.setLayout(new BorderLayout());
        final JTextField filePathTextField = new JTextField();
        filePathTextField.setText(controller.getCurrentFile());
        filePathTextField.setEditable(false);
        fileChooserPanel.add(filePathTextField, BorderLayout.CENTER);
        final JButton browseButton = new JButton("Browse");
        fileChooserPanel.add(browseButton, BorderLayout.EAST);
        canvas.add(fileChooserPanel, BorderLayout.NORTH);
        // text area
        final JTextArea textArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
        final JScrollPane textAreaScrollPane = new JScrollPane(textArea);
        canvas.add(textAreaScrollPane, BorderLayout.CENTER);
        // save button
        final JButton saveButton = new JButton("Save");
        canvas.add(saveButton, BorderLayout.SOUTH);
        /*
         * Handlers
         */
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser chooser = new JFileChooser();
                final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("Text files", "txt");
                chooser.setFileFilter(extensionFilter);
                chooser.setCurrentDirectory(new File(controller.getCurrentFile()));
                final int returnValue = chooser.showOpenDialog(frame);
                switch (returnValue) {
                    case JFileChooser.APPROVE_OPTION:
                        controller.setCurrentFile(chooser.getSelectedFile().getAbsolutePath());
                        filePathTextField.setText(controller.getCurrentFile());
                        break;

                    case JFileChooser.ERROR_OPTION:
                        JOptionPane.showMessageDialog(frame, "Oops something went wrong :(", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        break;

                    case JFileChooser.CANCEL_OPTION:
                    default:
                        break;
                }
            }
        });
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
        // init frame
        frame.setContentPane(canvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

    public static void main(final String... args) {
        new SimpleGUIWithFileChooser();
    }
}
