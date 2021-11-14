import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class DecimalCalculationPanel extends CalculatorPanel {
    private final JTextField operand1 = new JTextField(10);
    private final JTextField operand2 = new JTextField(10);
    private final JComboBox<String> operationComboBox = new JComboBox<>();

    public DecimalCalculationPanel() {
        super("Decimal Calculation—Add, Subtract, Multiply, or Divide");

        this.operationComboBox.addItem("+");
        this.operationComboBox.addItem("-");
        this.operationComboBox.addItem("*");
        this.operationComboBox.addItem("/");
        this.operationComboBox.addActionListener(e -> setResult(""));

        calculateButton.addActionListener(e -> {
            BigDecimal num1;
            try {
                num1 = new BigDecimal(this.operand1.getText());
            } catch (Exception invalid_e) {
                num1 = null;
            }
            BigDecimal num2;
            try {
                num2 = new BigDecimal(this.operand2.getText());
            } catch (Exception invalid_e) {
                num2 = null;
            }

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
                            var resultTemp = num1.divide(num2, RoundingMode.HALF_UP);
                            this.setResult(resultTemp.toString());
                        } else {
                            this.setResult("Error: Divide by 0");
                        }
                    }
                    default -> this.setResult("ERROR");
                }
            } else {
                this.setResult("Error: Invalid input");
            }
        });
        topPanel.add(new NormalTextLine("Decimal value:"));
        topPanel.add(operand1);
        topPanel.add(operationComboBox);
        topPanel.add(operand2);
        topPanel.add(new NormalTextLine("= ?"));
        middlePanel.add(new NormalTextLine("Result:"));
        middlePanel.add(result);
    }

    public void reset() {
        super.reset();
        operand1.setText("");
        operand2.setText("");
    }
}
