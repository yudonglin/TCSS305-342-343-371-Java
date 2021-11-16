import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ObjectOrientedGUICalculator extends JFrame {

    // a hash map that is used to store primary panels
    private final HashMap<String, JPanel> calculationPanelsHashMap = new HashMap<>();

    /**
     * create a calculator window that will handle all kinds of calculations
     *
     * @param width  the width of the window
     * @param height the height of the window
     */
    public ObjectOrientedGUICalculator(int width, int height) {

        this.setTitle("Ultimate Calculator");
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

        // initialize primary panels
        final String[] panelsNames = {"hex", "binary", "bigNum", "decimal"};
        for (String name : panelsNames) {
            this.calculationPanelsHashMap.put(name, new JPanel());
            this.calculationPanelsHashMap.get(name).setLayout(new GridLayout(3, 1));
            this.calculationPanelsHashMap.get(name).setVisible(false);
            this.add(calculationPanelsHashMap.get(name));
        }

        //Hexadecimal Calculation Panel
        this.calculationPanelsHashMap.get("hex").add(new HexadecimalCalculationPanel());
        this.calculationPanelsHashMap.get("hex").add(new HexadecimalToDecimalPanel());
        this.calculationPanelsHashMap.get("hex").add(new DecimalToHexadecimalPanel());

        //Binary Calculation Panel
        this.calculationPanelsHashMap.get("binary").add(new BinaryCalculationPanel());
        this.calculationPanelsHashMap.get("binary").add(new BinaryToDecimalPanel());
        this.calculationPanelsHashMap.get("binary").add(new DecimalToBinaryPanel());

        //Big Number Calculation Panel
        this.calculationPanelsHashMap.get("bigNum").add(new BigNumberCalculationPanel());
        this.calculationPanelsHashMap.get("bigNum").setLayout(new GridLayout(1, 1));

        //Decimal Calculation Panel
        this.calculationPanelsHashMap.get("decimal").add(new DecimalCalculationPanel());

        //add action listeners to menu items
        hexadecimalCalculationMenuItem.addActionListener(e -> this.switchToPanel("hex"));
        binaryCalculationMenuItem.addActionListener(e -> this.switchToPanel("binary"));
        bigNumberCalculationMenuItem.addActionListener(e -> this.switchToPanel("bigNum"));
        decimalCalculationMenuItem.addActionListener(e -> this.switchToPanel("decimal"));

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

    /**
     * @param name the name of panel that need to switch to
     */
    private void switchToPanel(String name) {
        for (String panelName : this.calculationPanelsHashMap.keySet()) {
            this.calculationPanelsHashMap.get(panelName).setVisible(panelName.equals(name));
        }
    }

}
