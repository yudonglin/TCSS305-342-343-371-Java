import javax.swing.*;
import java.awt.*;

public class ObjectOrientedGUICalculator {

    public static void main(String[] args) {
        // write your code here
        new GUICalculator(600, 600);
    }
}


class GUICalculator extends JFrame {


    public GUICalculator(int width, int height) {

        this.setTitle("Ultimate Calculator");
        // TODO add an icon
        //Frame.setIconImage(Toolkit.getDefaultToolkit().getImage(“meme.jpg”))
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLayout(new CardLayout(1, 1));

        // add menu
        var menuBar = new JMenuBar();
        var calculatorMenu = new JMenu("Calculator");
        menuBar.add(calculatorMenu);

        var hexadecimalCalculationMenuItem = new JMenuItem("Hexadecimal Calculation");
        var binaryCalculationMenuItem = new JMenuItem("Binary Calculation");
        calculatorMenu.add(hexadecimalCalculationMenuItem);
        calculatorMenu.add(binaryCalculationMenuItem);
        this.setJMenuBar(menuBar);

        //Hexadecimal Calculation
        JPanel mainHexadecimalCalculationPanel = new JPanel();
        mainHexadecimalCalculationPanel.setLayout(new GridLayout(3, 1));
        mainHexadecimalCalculationPanel.setVisible(false);

        this.add(mainHexadecimalCalculationPanel);

        mainHexadecimalCalculationPanel.add(new HexadecimalCalculationPanel());
        mainHexadecimalCalculationPanel.add(new HexadecimalToDecimalPanel());
        mainHexadecimalCalculationPanel.add(new DecimalToHexadecimalPanel());

        //Binary Calculation
        JPanel mainBinaryCalculationPanel = new JPanel();
        mainBinaryCalculationPanel.setLayout(new GridLayout(3, 1));
        mainBinaryCalculationPanel.setVisible(false);

        this.add(mainBinaryCalculationPanel);

        mainBinaryCalculationPanel.add(new BinaryCalculationPanel());
        mainBinaryCalculationPanel.add(new BinaryToDecimalPanel());
        mainBinaryCalculationPanel.add(new DecimalToBinaryPanel());

        hexadecimalCalculationMenuItem.addActionListener(e -> {
            mainBinaryCalculationPanel.setVisible(false);
            mainHexadecimalCalculationPanel.setVisible(true);
        });

        binaryCalculationMenuItem.addActionListener(e -> {
            mainHexadecimalCalculationPanel.setVisible(false);
            mainBinaryCalculationPanel.setVisible(true);
        });

        // show menu
        this.setVisible(true);
    }

}
