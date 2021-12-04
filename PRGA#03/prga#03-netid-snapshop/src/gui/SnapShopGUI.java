package gui;

import filters.*;
import image.PixelImage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.HashMap;


/**
 * a class that extends JFrame and manage the gui and Image modifier
 */
public class SnapShopGUI extends JFrame {

    // the main panel of the frame
    private final JPanel screen = new JPanel();
    // the hash map that will be used to store buttons for filter
    private final HashMap<String, JButton> imageActionButtonsHashMap = new HashMap<>(7);
    // the JFileChooser that will be used to select files
    private final JFileChooser imageChooser = new JFileChooser(FileSystems.getDefault().getPath(".").toString());
    // open button
    private final JButton openButton = new JButton("Open...");
    // save button
    private final JButton saveAsButton = new JButton("Save As...");
    // close button
    private final JButton closeImageButton = new JButton("Close Image");
    // a JLabel that will be used to show image
    private final JLabel imageViewer = new JLabel();
    // the path of icon image
    private final String iconImagePath = FileSystems.getDefault().getPath("sample_images/huskies_logo.jpg").toAbsolutePath().toString();
    // the image that is current modifying
    private PixelImage imageIsModifying;
    // the extension of the image that is current modifying
    private String extensionOfCurrentImage;


    /**
     * the constructor, call test() when init
     */
    public SnapShopGUI() {
        super();
        this.test();
    }

    /**
     * the JUnit tests
     */
    @Test
    private void test() {
        Assertions.assertEquals(".jpg", this.getExtension("sample_images/huskies_logo.jpg"));
        Assertions.assertEquals("", this.getExtension("sample_images/huskies_logo/folder"));
        // ensure the icon has been set
        Assertions.assertNotEquals("", this.iconImagePath);
        Assertions.assertNotNull(this.iconImagePath);
    }


    /**
     * initialize the gui and critical data
     */
    public void start() {
        //javax.swing.JOptionPane.showMessageDialog(null, "SnapShop placeholder");

        this.setTitle("TCSS 305 – Programming Assignment 3 (ydlin)");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(iconImagePath));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // add the main panel to the frame
        this.add(screen);

        // set main panel layout to BorderLayout
        screen.setLayout(new BorderLayout());

        // init upper panel
        var upperPanel = new JPanel();
        screen.add(upperPanel, BorderLayout.NORTH);
        // init and create filters
        AbstractFilter[] imageActionButtonsStr = {
                new EdgeDetectFilter(), new EdgeHighlightFilter(), new FlipHorizontalFilter(), new FlipVerticalFilter(), new GrayscaleFilter(), new SharpenFilter(), new SoftenFilter()
        };
        // init buttons (I use a hash map to store all the buttons)
        for (AbstractFilter filter_t : imageActionButtonsStr) {
            // create a button
            var button_t = new JButton(filter_t.getDescription());
            button_t.setEnabled(false);
            // add ActionListener to target button, so it will know which filter to use
            button_t.addActionListener(e -> {
                filter_t.filter(imageIsModifying);
                // reset the imageViewer show it will show updated image
                imageViewer.setIcon(new ImageIcon(imageIsModifying));
            });
            button_t.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(FileSystems.getDefault().getPath("icons/paint.png").toAbsolutePath().toString())));
            // put the button into the map
            imageActionButtonsHashMap.put(filter_t.getDescription(), button_t);
            // add the button to the upper panel
            upperPanel.add(button_t);
        }

        // add imageViewer to the main panel (which is used to display image)
        imageViewer.setHorizontalAlignment(SwingConstants.CENTER);
        screen.add(imageViewer, BorderLayout.CENTER);


        // init lower panel
        var lowerPanel = new JPanel();
        screen.add(lowerPanel, BorderLayout.SOUTH);
        // add buttons
        openButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(FileSystems.getDefault().getPath("icons/open.gif").toAbsolutePath().toString())));
        lowerPanel.add(openButton);
        saveAsButton.setEnabled(false);
        saveAsButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(FileSystems.getDefault().getPath("icons/save.gif").toAbsolutePath().toString())));
        lowerPanel.add(saveAsButton);
        closeImageButton.setEnabled(false);
        closeImageButton.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage(FileSystems.getDefault().getPath("icons/close.gif").toAbsolutePath().toString())));
        lowerPanel.add(closeImageButton);

        // add ActionListener to open button
        openButton.addActionListener(e -> {
            if (imageChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                var file = imageChooser.getSelectedFile();
                try {
                    imageIsModifying = PixelImage.load(file);
                    imageViewer.setIcon(new ImageIcon(imageIsModifying));
                    extensionOfCurrentImage = this.getExtension(file.getAbsolutePath());
                    this.setActionButtonsEnabled(true);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "The selected file " + file.getAbsolutePath() + " did not contain an image!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
            this.pack();
        });

        // add ActionListener to save button
        saveAsButton.addActionListener(e -> {
            if (imageChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                var file = imageChooser.getSelectedFile();
                // check whether the user keeps the same extension, if not, then need to double-check with the user
                if (file.getPath().endsWith(extensionOfCurrentImage) || JOptionPane.showConfirmDialog(this, "Saving the image with different extension is highly not recommended, are you sure that you want to continue?", "Warning!", JOptionPane.YES_NO_OPTION) == 0) {
                    try {
                        imageIsModifying.save(file);
                        this.setActionButtonsEnabled(true);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(this, "Fail to save image.", "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // add ActionListener to close button
        closeImageButton.addActionListener(e -> {
            // stop showing image
            imageViewer.setIcon(null);
            // disable all button except open button
            this.setActionButtonsEnabled(false);
            // resize window
            this.pack();
        });

        // resize window
        this.pack();
        // set minimum size of the frame
        this.setMinimumSize(this.getSize());
        // start showing content
        this.setVisible(true);

    }

    /**
     * enable or disable all buttons except the open button
     *
     * @param status whether enable or disable all buttons
     */
    private void setActionButtonsEnabled(boolean status) {
        for (JButton actionButton : imageActionButtonsHashMap.values()) {
            actionButton.setEnabled(status);
        }
        saveAsButton.setEnabled(status);
        closeImageButton.setEnabled(status);
    }

    /**
     * get a path of a file, return the extension of the file
     *
     * @param _path the path of a file
     * @return the extension of the file
     */
    private String getExtension(String _path) {
        int _index = _path.lastIndexOf('.');
        return _index > 0 ? _path.substring(_index) : "";
    }
}