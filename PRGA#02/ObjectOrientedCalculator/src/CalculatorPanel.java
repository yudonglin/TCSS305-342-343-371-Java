import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;
import java.util.Objects;

/**
 * The basic structure of all primary panels
 */
abstract class CalculatorPanel extends JPanel {

    protected final JPanel topPanel = new JPanel();
    protected final JPanel middlePanel = new JPanel();
    protected final JPanel bottomPanel = new JPanel();
    protected final JPanel desPanel = new JPanel();
    // trigger the calculation action
    protected final JButton calculateButton = new JButton("Calculate");
    // the text filed that is used to show result or error message
    private final JTextField result = new JTextField(10);

    /**
     * @param title the title of the panel (the first line text)
     */
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

    /**
     * clear the result text field
     */
    public void reset() {
        setResult("");
    }

    /**
     * @return the result text field itself, not the content
     */
    public JTextField getResult() {
        return result;
    }

    /**
     * reset the result text field's content
     */
    public void setResult(String text) {
        this.result.setText(text);
        this.result.setColumns(Math.max(this.result.getText().length(), 10));
        this.updateUI();
    }
}

/**
 * The basic structure of BinaryCalculationPanel and HexadecimalCalculationPanel
 */
abstract class CalculationPanel extends CalculatorPanel {

    private final JTextField operand1 = new JTextField(10);
    private final JTextField operand2 = new JTextField(10);
    private final JComboBox<String> operationComboBox = new JComboBox<>();
    private final JTextField equationInDecimal = new NormalTextLine("Decimal value: ? + ? = ?");

    /**
     * @param valueType what type of data that panel will need to deal with (Binary or Hexadecimal)
     */
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
        // try to generate co-response data object use user input
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
                        if (num2.toDecimal().compareTo(BigInteger.ZERO) != 0) {
                            var resultTemp = num1.divide(num2);
                            var remainderTemp = num1.mod(num2);
                            if (remainderTemp.toDecimal().compareTo(BigInteger.ZERO) != 0) {
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
        topPanel.add(getResult());
    }

    /**
     * @param text the value that will be used to create return GeneralDataType
     * @return a Binary or Hexadecimal depend on which kind of panel
     */
    protected abstract GeneralDataType creatNumObject(String text);

    /**
     * reset all primary text fields' contents
     */
    public void reset() {
        super.reset();
        operand1.setText("");
        operand2.setText("");
        equationInDecimal.setText("Decimal value: ? " + Objects.requireNonNull(operationComboBox.getSelectedItem()) + " ? = ?");
    }

}


/**
 * The basic structure of BinaryToDecimalPanel and HexadecimalToDecimalPanel
 */
abstract class ToDecimalPanel extends CalculatorPanel {

    private final JTextField inputValue = new JTextField(10);

    /**
     * @param valueType what type of data that panel will need to deal with (Binary or Hexadecimal)
     */
    public ToDecimalPanel(String valueType) {
        super("Convert " + valueType + " Value to Decimal Value");
        calculateButton.addActionListener(e -> {
            String text;
            try {
                text = String.valueOf(creatNumObject(this.inputValue.getText()).toDecimal());
            } catch (Exception invalid_e) {
                text = "Error: " + (this.inputValue.getText().length() > 0 ? invalid_e.getMessage() : "input text filed is empty");
            }
            this.setResult(text);
        });
        topPanel.add(new NormalTextLine(valueType + " Value: "));
        topPanel.add(inputValue);
        topPanel.add(new NormalTextLine("= ?"));
        middlePanel.add(new NormalTextLine("Decimal:"));
        middlePanel.add(getResult());
    }

    /**
     * @param text the value that will be used to create return GeneralDataType
     * @return a Binary or Hexadecimal depend on which kind of panel
     */
    protected abstract GeneralDataType creatNumObject(String text);

    /**
     * reset all primary text fields' contents
     */
    public void reset() {
        super.reset();
        inputValue.setText("");
    }
}

/**
 * The basic structure of DecimalToBinaryPanel and DecimalToHexadecimalPanel
 */
abstract class FromDecimalPanel extends CalculatorPanel {
    private final JTextField inputValue = new JTextField(10);

    /**
     * @param valueType what type of data that panel will need to deal with (Binary or Hexadecimal)
     */
    public FromDecimalPanel(String valueType) {
        super("Convert Decimal Value to " + valueType + " Value");
        calculateButton.addActionListener(e -> {
            String text;
            try {
                text = String.valueOf(this.fromDecimal(this.inputValue.getText()));
            } catch (Exception invalid_e) {
                text = "Error: " + (this.inputValue.getText().length() > 0 ? invalid_e.getMessage() : "input text filed is empty");
            }
            this.setResult(text);
        });
        topPanel.add(new NormalTextLine("Decimal Value: "));
        topPanel.add(inputValue);
        topPanel.add(new NormalTextLine("= ?"));
        middlePanel.add(new NormalTextLine(valueType + ":"));
        middlePanel.add(getResult());
    }

    /**
     * @param text the raw value that will be used to generate return GeneralDataType
     * @return a Binary or Hexadecimal depend on which kind of panel
     */
    protected abstract GeneralDataType fromDecimal(String text);

    /**
     * reset all primary text fields' contents
     */
    public void reset() {
        super.reset();
        inputValue.setText("");
    }
}
