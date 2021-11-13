import javax.swing.*;
import java.util.Objects;

/**
 * A {@code Binary} object is the child class of GeneralDataType.
 * It will pass the {@param value} to the super class {@code GeneralDataType} as well as 2 as {@param positionalNotation}.
 */
class Binary extends GeneralDataType {
    public Binary(String value) {
        super(value, 2);
    }

    public static Binary fromDecimal(String value) {
        return new Binary(Long.toBinaryString(Long.parseLong(value)));
    }

    public Binary valueOf(long value) {
        return new Binary(Long.toBinaryString(value));
    }
}

class BinaryCalculationPanel extends CalculatorPanel {
    private final JTextField operand1 = new JTextField(10);
    private final JTextField operand2 = new JTextField(10);
    private final JComboBox<String> operationComboBox = new JComboBox<>();
    private final JTextField equationInDecimal = new NormalTextLine("Decimal value: ? + ? = ?");

    public BinaryCalculationPanel() {
        super("Binary Calculation—Add, Subtract, Multiply, or Divide");

        equationInDecimal.setEditable(false);
        equationInDecimal.setColumns(30);
        middlePanel.add(equationInDecimal);

        this.operationComboBox.addItem("+");
        this.operationComboBox.addItem("-");
        this.operationComboBox.addItem("*");
        this.operationComboBox.addItem("/");

        this.operationComboBox.addActionListener(e -> {
            equationInDecimal.setText("Decimal value: ? " + Objects.requireNonNull(operationComboBox.getSelectedItem()) + " ? = ?");
            result.setText("");
        });

        calculateButton.addActionListener(e -> {
            var num1 = new Binary(this.operand1.getText());
            var num2 = new Binary(this.operand2.getText());
            switch (Objects.requireNonNull(operationComboBox.getSelectedItem()).toString()) {
                case "+" -> {
                    var resultTemp = num1.add(num2);
                    this.result.setText(resultTemp.toString());
                    equationInDecimal.setText("Decimal value: " + num1.toDecimal() + " + " + num2.toDecimal() + " = " + resultTemp.toDecimal());
                }
                case "-" -> {
                    var resultTemp = num1.subtract(num2);
                    this.result.setText(resultTemp.toString());
                    equationInDecimal.setText("Decimal value: " + num1.toDecimal() + " - " + num2.toDecimal() + " = " + resultTemp.toDecimal());
                }
                case "*" -> {
                    var resultTemp = num1.multiply(num2);
                    this.result.setText(resultTemp.toString());
                    equationInDecimal.setText("Decimal value: " + num1.toDecimal() + " * " + num2.toDecimal() + " = " + resultTemp.toDecimal());
                }
                case "/" -> {
                    if (num2.toDecimal() != 0) {
                        var resultTemp = num1.divide(num2);
                        var remainderTemp = num1.modulo(num2);
                        if (remainderTemp.toDecimal() != 0) {
                            this.result.setText(resultTemp.toString() + " Remainder: " + remainderTemp);
                            equationInDecimal.setText("Decimal value: " + num1.toDecimal() + " * " + num2.toDecimal() + " = " + resultTemp.toDecimal() + " Remainder: " + remainderTemp.toDecimal());
                        } else {
                            this.result.setText(resultTemp.toString());
                            equationInDecimal.setText("Decimal value: " + num1.toDecimal() + " / " + num2.toDecimal() + " = " + resultTemp.toDecimal());
                        }
                    } else {
                        this.result.setText("ERROR: Divide by 0");
                        equationInDecimal.setText("Decimal value: " + num1.toDecimal() + " " + Objects.requireNonNull(operationComboBox.getSelectedItem()) + " " + num2.toDecimal() + " = ERROR");
                    }
                }
                default -> this.result.setText("ERROR");
            }
        });
        topPanel.add(new NormalTextLine("Binary value:"));
        topPanel.add(operand1);
        topPanel.add(operationComboBox);
        topPanel.add(operand2);
        topPanel.add(new NormalTextLine("="));
        topPanel.add(result);
    }

    public void reset() {
        super.reset();
        operand1.setText("");
        operand2.setText("");
        equationInDecimal.setText("Decimal value: ? " + Objects.requireNonNull(operationComboBox.getSelectedItem()) + " ? = ?");
    }
}

class BinaryToDecimalPanel extends CalculatorPanel {
    private final JTextField hexadecimalValue = new JTextField(10);

    public BinaryToDecimalPanel() {
        super("Convert Binary Value to Decimal Value");
        calculateButton.addActionListener(e -> this.result.setText(String.valueOf(new Binary(this.hexadecimalValue.getText()).toDecimal())));
        topPanel.add(new NormalTextLine("Binary Value: "));
        topPanel.add(hexadecimalValue);
        middlePanel.add(new NormalTextLine("="));
        middlePanel.add(result);
    }

    public void reset() {
        super.reset();
        hexadecimalValue.setText("");
    }
}

class DecimalToBinaryPanel extends CalculatorPanel {
    private final JTextField decimalValue = new JTextField(10);

    public DecimalToBinaryPanel() {
        super("Convert Decimal Value to Binary Value");
        calculateButton.addActionListener(e -> this.result.setText(String.valueOf(Binary.fromDecimal(this.decimalValue.getText()))));
        topPanel.add(new NormalTextLine("Decimal Value: "));
        topPanel.add(decimalValue);
        middlePanel.add(new NormalTextLine("="));
        middlePanel.add(result);
    }

    public void reset() {
        super.reset();
        decimalValue.setText("");
    }
}
