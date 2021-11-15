import javax.swing.*;
import java.awt.*;

public class ObjectOrientedGUICalculator extends JFrame {
    /**
     * create a calculator window that will handle all kinds of calculations
     *
     * @param width  the width of the window
     * @param height the height of the window
     */
    public ObjectOrientedGUICalculator(int width, int height) {

        this.setTitle("Ultimate Calculator");
        // TODO add an icon
        //Frame.setIconImage(Toolkit.getDefaultToolkit().getImage(“meme.jpg”))
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);
        this.setLayout(new CardLayout(1, 1));

        // create menu
        var menuBar = new JMenuBar();
        var calculatorMenu = new JMenu("Calculator");
        menuBar.add(calculatorMenu);

        //add menu items
        var hexadecimalCalculationMenuItem = new JMenuItem("Hexadecimal Calculation");
        calculatorMenu.add(hexadecimalCalculationMenuItem);
        var binaryCalculationMenuItem = new JMenuItem("Binary Calculation");
        calculatorMenu.add(binaryCalculationMenuItem);
        var bigNumberCalculationMenuItem = new JMenuItem("Big Number Calculation");
        calculatorMenu.add(bigNumberCalculationMenuItem);
        var decimalCalculationMenuItem = new JMenuItem("Decimal Calculation");
        calculatorMenu.add(decimalCalculationMenuItem);
        this.setJMenuBar(menuBar);

        //Hexadecimal Calculation Panel
        JPanel mainHexadecimalCalculationPanel = new JPanel();
        mainHexadecimalCalculationPanel.setLayout(new GridLayout(3, 1));
        mainHexadecimalCalculationPanel.setVisible(false);
        this.add(mainHexadecimalCalculationPanel);
        mainHexadecimalCalculationPanel.add(new HexadecimalCalculationPanel());
        mainHexadecimalCalculationPanel.add(new HexadecimalToDecimalPanel());
        mainHexadecimalCalculationPanel.add(new DecimalToHexadecimalPanel());

        //Binary Calculation Panel
        JPanel mainBinaryCalculationPanel = new JPanel();
        mainBinaryCalculationPanel.setLayout(new GridLayout(3, 1));
        mainBinaryCalculationPanel.setVisible(false);
        this.add(mainBinaryCalculationPanel);
        mainBinaryCalculationPanel.add(new BinaryCalculationPanel());
        mainBinaryCalculationPanel.add(new BinaryToDecimalPanel());
        mainBinaryCalculationPanel.add(new DecimalToBinaryPanel());

        //Big Number Calculation Panel
        var bigNumberCalculationPanel = new BigNumberCalculationPanel();
        bigNumberCalculationPanel.setVisible(false);
        this.add(bigNumberCalculationPanel);

        //Decimal Calculation Panel
        var decimalCalculationPanel = new DecimalCalculationPanel();
        decimalCalculationPanel.setVisible(false);
        this.add(decimalCalculationPanel);


        //add action listeners to menu items
        hexadecimalCalculationMenuItem.addActionListener(e -> {
            mainBinaryCalculationPanel.setVisible(false);
            bigNumberCalculationPanel.setVisible(false);
            decimalCalculationPanel.setVisible(false);
            mainHexadecimalCalculationPanel.setVisible(true);
        });

        binaryCalculationMenuItem.addActionListener(e -> {
            mainHexadecimalCalculationPanel.setVisible(false);
            bigNumberCalculationPanel.setVisible(false);
            decimalCalculationPanel.setVisible(false);
            mainBinaryCalculationPanel.setVisible(true);
        });

        bigNumberCalculationMenuItem.addActionListener(e -> {
            mainHexadecimalCalculationPanel.setVisible(false);
            mainBinaryCalculationPanel.setVisible(false);
            decimalCalculationPanel.setVisible(false);
            bigNumberCalculationPanel.setVisible(true);
        });

        decimalCalculationMenuItem.addActionListener(e -> {
            mainHexadecimalCalculationPanel.setVisible(false);
            mainBinaryCalculationPanel.setVisible(false);
            bigNumberCalculationPanel.setVisible(false);
            decimalCalculationPanel.setVisible(true);
        });

        // show menu
        this.setVisible(true);
    }

    /**
     * Create the calculator window
     *
     * @param args in this case, it will do nothing
     */
    public static void main(String[] args) {
        // write your code here
        new ObjectOrientedGUICalculator(700, 700);
    }

}
