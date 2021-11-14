import javax.swing.*;
import java.awt.*;
import java.util.Objects;

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
        setResult("");
    }

    public void setResult(String text) {
        this.result.setText(text);
        this.result.setColumns(Math.max(this.result.getText().length(), 10));
        this.updateUI();
    }

}

abstract class CalculationPanel extends CalculatorPanel {
    private final JTextField operand1 = new JTextField(10);
    private final JTextField operand2 = new JTextField(10);
    private final JComboBox<String> operationComboBox = new JComboBox<>();
    private final JTextField equationInDecimal = new NormalTextLine("Decimal value: ? + ? = ?");

    public CalculationPanel(String valueType) {
        super(valueType + " Calculation—Add, Subtract, Multiply, or Divide");

        equationInDecimal.setEditable(false);
        equationInDecimal.setColumns(30);
        middlePanel.add(equationInDecimal);

        this.operationComboBox.addItem("+");
        this.operationComboBox.addItem("-");
        this.operationComboBox.addItem("*");
        this.operationComboBox.addItem("/");

        this.operationComboBox.addActionListener(e -> {
            equationInDecimal.setText("Decimal value: ? " + Objects.requireNonNull(operationComboBox.getSelectedItem()) + " ? = ?");
            setResult("");
        });

        calculateButton.addActionListener(e -> {
            GeneralDataType num1;
            try {
                num1 = creatNumObject(this.operand1.getText());
            } catch (Exception invalid_e) {
                num1 = null;
            }
            GeneralDataType num2;
            try {
                num2 = creatNumObject(this.operand2.getText());
            } catch (Exception invalid_e) {
                num2 = null;
            }

            if (num1 != null && num2 != null) {
                switch (Objects.requireNonNull(operationComboBox.getSelectedItem()).toString()) {
                    case "+" -> {
                        var resultTemp = num1.add(num2);
                        this.setResult(resultTemp.toString());
                        equationInDecimal.setText("Decimal value: " + num1.toDecimal() + " + " + num2.toDecimal() + " = " + resultTemp.toDecimal());
                    }
                    case "-" -> {
                        var resultTemp = num1.subtract(num2);
                        this.setResult(resultTemp.toString());
                        equationInDecimal.setText("Decimal value: " + num1.toDecimal() + " - " + num2.toDecimal() + " = " + resultTemp.toDecimal());
                    }
                    case "*" -> {
                        var resultTemp = num1.multiply(num2);
                        this.setResult(resultTemp.toString());
                        equationInDecimal.setText("Decimal value: " + num1.toDecimal() + " * " + num2.toDecimal() + " = " + resultTemp.toDecimal());
                    }
                    case "/" -> {
                        if (num2.toDecimal() != 0) {
                            var resultTemp = num1.divide(num2);
                            var remainderTemp = num1.modulo(num2);
                            if (remainderTemp.toDecimal() != 0) {
                                this.setResult(resultTemp.toString() + " Remainder: " + remainderTemp);
                                equationInDecimal.setText("Decimal value: " + num1.toDecimal() + " * " + num2.toDecimal() + " = " + resultTemp.toDecimal() + " Remainder: " + remainderTemp.toDecimal());
                            } else {
                                this.setResult(resultTemp.toString());
                                equationInDecimal.setText("Decimal value: " + num1.toDecimal() + " / " + num2.toDecimal() + " = " + resultTemp.toDecimal());
                            }
                        } else {
                            this.setResult("Error: Divide by 0");
                            equationInDecimal.setText("Decimal value: " + num1.toDecimal() + " " + Objects.requireNonNull(operationComboBox.getSelectedItem()) + " " + num2.toDecimal() + " = ERROR");
                        }
                    }
                    default -> this.setResult("ERROR");
                }
            } else {
                this.setResult("Error: Invalid input");
                equationInDecimal.setText(
                        "Decimal value: "
                                + (num1 == null ? "Invalid Input" : num1.toString())
                                + " " + Objects.requireNonNull(operationComboBox.getSelectedItem())
                                + " " + (num2 == null ? "Invalid Input" : num2.toString()) + " = ERROR"
                );
            }

        });
        topPanel.add(new NormalTextLine(valueType + " value:"));
        topPanel.add(operand1);
        topPanel.add(operationComboBox);
        topPanel.add(operand2);
        topPanel.add(new NormalTextLine("="));
        topPanel.add(result);
    }

    protected abstract GeneralDataType creatNumObject(String text);

    public void reset() {
        super.reset();
        operand1.setText("");
        operand2.setText("");
        equationInDecimal.setText("Decimal value: ? " + Objects.requireNonNull(operationComboBox.getSelectedItem()) + " ? = ?");
    }

}


abstract class ToDecimalPanel extends CalculatorPanel {
    private final JTextField inputValue = new JTextField(10);

    public ToDecimalPanel(String valueType) {
        super("Convert " + valueType + " Value to Decimal Value");
        calculateButton.addActionListener(e -> {
            String text;
            try {
                text = String.valueOf(creatNumObject(this.inputValue.getText()).toDecimal());
            } catch (Exception invalid_e) {
                text = "Error: " + invalid_e.getMessage();
            }
            this.setResult(text);
        });
        topPanel.add(new NormalTextLine(valueType + " Value: "));
        topPanel.add(inputValue);
        topPanel.add(new NormalTextLine("= ?"));
        middlePanel.add(new NormalTextLine("Decimal:"));
        middlePanel.add(result);
    }

    protected abstract GeneralDataType creatNumObject(String text);

    public void reset() {
        super.reset();
        inputValue.setText("");
    }
}

abstract class FromDecimalPanel extends CalculatorPanel {
    private final JTextField inputValue = new JTextField(10);

    public FromDecimalPanel(String valueType) {
        super("Convert Decimal Value to " + valueType + " Value");
        calculateButton.addActionListener(e -> {
            String text;
            try {
                text = String.valueOf(this.fromDecimal(this.inputValue.getText()));
            } catch (Exception invalid_e) {
                text = "Error: " + invalid_e.getMessage();
            }
            this.setResult(text);
        });
        topPanel.add(new NormalTextLine("Decimal Value: "));
        topPanel.add(inputValue);
        topPanel.add(new NormalTextLine("= ?"));
        middlePanel.add(new NormalTextLine(valueType + ":"));
        middlePanel.add(result);
    }

    protected abstract GeneralDataType fromDecimal(String text);

    public void reset() {
        super.reset();
        inputValue.setText("");
    }
}
