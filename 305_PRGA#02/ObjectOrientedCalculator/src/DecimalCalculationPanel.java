import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class DecimalCalculationPanel extends CalculatorPanel {
    private final JTextField operand1 = new JTextField(10);
    private final JTextField operand2 = new JTextField(10);
    private final JComboBox<String> operationComboBox = new JComboBox<>();

    /**
     * create the panel for Decimal Calculation
     */
    public DecimalCalculationPanel() {
        super("Decimal Calculation—Add, Subtract, Multiply, or Divide");

        // add operations
        this.operationComboBox.addItem("+");
        this.operationComboBox.addItem("-");
        this.operationComboBox.addItem("*");
        this.operationComboBox.addItem("/");
        this.operationComboBox.addActionListener(e -> setResult(""));

        // add ActionListener to the calculate button
        calculateButton.addActionListener(e -> {
            BigDecimal num1;
            // check if operand 1 has valid input value
            try {
                num1 = new BigDecimal(this.operand1.getText());
            } catch (Exception invalid_e) {
                num1 = null;
            }
            BigDecimal num2;
            // check if operand 2 has valid input value
            try {
                num2 = new BigDecimal(this.operand2.getText());
            } catch (Exception invalid_e) {
                num2 = null;
            }
            // if everything is fine
            if (num1 != null && num2 != null) {
                switch (Objects.requireNonNull(operationComboBox.getSelectedItem()).toString()) {
                    case "+" -> {
                        var resultTemp = num1.add(num2);
                        this.setResult(resultTemp.toString());
                    }
                    case "-" -> {
                        var resultTemp = num1.subtract(num2);
                        this.setResult(resultTemp.toString());
                    }
                    case "*" -> {
                        var resultTemp = num1.multiply(num2);
                        this.setResult(resultTemp.toString());
                    }
                    case "/" -> {
                        if (BigDecimal.ZERO.compareTo(num2) != 0) {
                            var resultTemp = num1.divide(num2, 5, RoundingMode.HALF_UP);
                            this.setResult(resultTemp.toString());
                        } else {
                            this.setResult("Error: Divide by 0");
                        }
                    }
                    default -> this.setResult("ERROR");
                }
            }
            // Ok, something must goes wrong...
            else if (num1 == null && num2 == null) {
                this.setResult("Error: Invalid inputs for both operand 1 and operand 2");
            } else if (num1 == null) {
                this.setResult("Error: Invalid input for operand 1");
            } else {
                this.setResult("Error: Invalid input for operand 2");
            }
        });
        // add all the essential components
        topPanel.add(new NormalTextLine("Decimal value:"));
        topPanel.add(operand1);
        topPanel.add(operationComboBox);
        topPanel.add(operand2);
        topPanel.add(new NormalTextLine("= ?"));
        middlePanel.add(new NormalTextLine("Result:"));
        middlePanel.add(getResult());
    }

    /**
     * reset primary text fields' contents
     */
    public void reset() {
        super.reset();
        operand1.setText("");
        operand2.setText("");
    }
}
