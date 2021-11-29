// finish (and comment) me!

package gui;

import filters.*;
import image.PixelImage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.HashMap;

/**
 * @author ???
 * @version ???
 */
public class SnapShopGUI extends JFrame {

    private final JPanel screen = new JPanel();

    private final HashMap<String, JButton> imageActionButtonsHashMap = new HashMap<>(7);

    private final JFileChooser imageChooser = new JFileChooser(FileSystems.getDefault().getPath(".").toString());
    private final JButton openButton = new JButton("Open...");
    private final JButton saveAsButton = new JButton("Save As...");
    private final JButton closeImageButton = new JButton("Close Image");
    private final JLabel imageViewer = new JLabel();
    private PixelImage imageIsModifying;

    public SnapShopGUI() {
    }

    /**
     *
     */
    public void start() {
        //javax.swing.JOptionPane.showMessageDialog(null, "SnapShop placeholder");
        init();
    }

    private void setActionButtonsEnabled(boolean status) {
        for (JButton actionButton : imageActionButtonsHashMap.values()) {
            actionButton.setEnabled(status);
        }
        saveAsButton.setEnabled(status);
        closeImageButton.setEnabled(status);
    }


    public void init() {

        this.setTitle("TCSS 305 – Programming Assignment 3 (ydlin)");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(FileSystems.getDefault().getPath("sample_images/huskies_logo.jpg").toAbsolutePath().toString()));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(screen);
        screen.removeAll();
        screen.setLayout(new BorderLayout());

        // init upper panel
        var upPanel = new JPanel();
        screen.add(upPanel, BorderLayout.NORTH);
        //add buttons
        var imageActionButtonsStr = new AbstractFilter[]{
                new EdgeDetectFilter(), new EdgeHighlightFilter(), new FlipHorizontalFilter(), new FlipVerticalFilter(), new GrayscaleFilter(), new SharpenFilter(), new SoftenFilter()
        };
        imageActionButtonsHashMap.clear();
        for (AbstractFilter filter_t : imageActionButtonsStr) {
            var button_t = new JButton(filter_t.getDescription());
            button_t.setEnabled(false);
            button_t.addActionListener(e -> {
                filter_t.filter(imageIsModifying);
                imageViewer.setIcon(new ImageIcon(imageIsModifying));
            });
            imageActionButtonsHashMap.put(filter_t.getDescription(), button_t);
            upPanel.add(button_t);
        }

        var middleImagePanel = new JPanel();
        screen.add(middleImagePanel, BorderLayout.CENTER);
        middleImagePanel.add(imageViewer);


        imageActionButtonsHashMap.get("Edge Detect").addActionListener(e -> {
            new EdgeDetectFilter().filter(imageIsModifying);
            imageViewer.setIcon(new ImageIcon(imageIsModifying));
        });

        // init lower panel
        var lowerPanel = new JPanel();
        screen.add(lowerPanel, BorderLayout.SOUTH);
        // add buttons
        lowerPanel.add(openButton);
        saveAsButton.setEnabled(false);
        lowerPanel.add(saveAsButton);
        closeImageButton.setEnabled(false);
        lowerPanel.add(closeImageButton);

        // add ActionListener to open button
        openButton.addActionListener(e -> {
            if (imageChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                var file = imageChooser.getSelectedFile();
                try {
                    imageIsModifying = PixelImage.load(file);
                    imageViewer.setIcon(new ImageIcon(imageIsModifying));
                    this.setActionButtonsEnabled(true);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "The select filed " + file.getAbsolutePath() + " did not contain an image!", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
            this.pack();
        });

        saveAsButton.addActionListener(e -> {
            if (imageChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                var file = imageChooser.getSelectedFile();
                try {
                    imageIsModifying.save(file);
                    this.setActionButtonsEnabled(true);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Eggs are not supposed to be green.", "Error!", JOptionPane.ERROR_MESSAGE);
                }
            }
            this.pack();
        });

        closeImageButton.addActionListener(e -> {
            imageViewer.setIcon(null);
            this.setActionButtonsEnabled(false);
            this.pack();
        });

        this.setVisible(true);
        this.pack();
    }


}