// finish (and comment) me!

package gui;

import javax.swing.*;
import java.awt.*;

/**
 * 
 * @author ???
 * @version ???
 */
public class SnapShopGUI extends JFrame {
    
    /**
     * 
     */
    public void start() {
        //javax.swing.JOptionPane.showMessageDialog(null, "SnapShop placeholder");
        init(100,100);
    }

    private void init(int width, int height) {
        this.setTitle("TCSS 305 – Programming Assignment 3 (<ydlin>)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLayout(new GridLayout(2, 1));

        var upPanel = new JPanel();



        this.setVisible(true);
    }


}