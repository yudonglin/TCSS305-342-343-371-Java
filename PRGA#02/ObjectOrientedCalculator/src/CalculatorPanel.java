import javax.swing.*;
import java.awt.*;

abstract class CalculatorPanel extends JPanel {
    protected final JTextField result = new JTextField(10);
    protected JPanel topPanel = new JPanel();
    protected JPanel middlePanel = new JPanel();
    protected JPanel bottomPanel = new JPanel();
    protected JPanel desPanel = new JPanel();
    protected JButton calculateButton = new JButton("Calculate");

    public CalculatorPanel(String title) {
        super(new GridLayout(4, 1));
        desPanel.add(new NormalTextLine(title));
        this.result.setEditable(false);
        this.add(desPanel);
        this.add(topPanel);
        this.add(middlePanel);
        this.add(bottomPanel);

        // add Calculate button
        bottomPanel.add(calculateButton);
        // add Clear button
        var clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> this.reset());
        bottomPanel.add(clearButton);
    }

    public void reset() {
        result.setText("");
    }

}
