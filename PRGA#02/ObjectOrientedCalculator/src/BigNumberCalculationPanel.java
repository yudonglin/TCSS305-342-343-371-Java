import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;

public class BigNumberCalculationPanel extends CalculatorPanel {

    // primary input text fields
    private final JTextField operand1 = new JTextField(10);
    private final JTextField operand2 = new JTextField(10);
    private final JTextField precision = new JTextField(5);
    // error messages
    private final NormalTextLine errorMsgForOperand1 = new NormalTextLine("");
    private final NormalTextLine errorMsgForOperand2 = new NormalTextLine("");
    private final NormalTextLine errorMsgForPrecision = new NormalTextLine("");
    // current action
    private final NormalTextLine currentAction = new NormalTextLine("Waiting for choosing one.");
    // variables for store data that will be used for calculation
    private BigDecimal BigDecimalOperand1;
    private BigDecimal BigDecimalOperand2;
    private BigInteger BigIntegerOperand1;
    private BigInteger BigIntegerOperand2;
    // precision, but in int
    private int precisionInInt;

    /**
     * create the panel for Big Number Calculation
     */
    public BigNumberCalculationPanel() {
        super("Big Number Calculator");
        this.setLayout(new GridLayout(9, 1));

        var moreDesPanel = new JPanel();
        moreDesPanel.add(new NormalTextLine("The calculator below can compute very large numbers. Acceptable formats include: integers, decimal,"));
        moreDesPanel.add(new NormalTextLine("or the E-notation form of scientific notation, i.e. 23E18, 3.5e19, etc."));
        this.add(moreDesPanel, 1);

        topPanel.add(new NormalTextLine("X ="));
        topPanel.add(operand1);
        topPanel.add(errorMsgForOperand1);

        middlePanel.add(new NormalTextLine("Y ="));
        middlePanel.add(operand2);
        middlePanel.add(errorMsgForOperand2);

        bottomPanel.removeAll();
        bottomPanel.add(new NormalTextLine("Precision:"));
        precision.setText("20");
        bottomPanel.add(precision);
        bottomPanel.add(new NormalTextLine("digits after the decimal place in the result"));
        bottomPanel.add(errorMsgForPrecision);

        var currentActionPanel = new JPanel();
        currentActionPanel.add(currentAction);
        this.add(currentActionPanel);

        var resultPanel = new JPanel();
        resultPanel.add(new NormalTextLine("Result:"));
        resultPanel.add(getResult());
        this.add(resultPanel);

        var actionDesPanel = new JPanel();
        actionDesPanel.add(new NormalTextLine("Click the buttons below to calculate"));
        this.add(actionDesPanel);

        // panel for manage action buttons
        var actionButtonsPanel = new JPanel();
        this.add(actionButtonsPanel);

        // create an array for storing action buttons
        JButton[] actionButtonsArray = new JButton[11];

        // add
        actionButtonsArray[0] = new JButton("X + Y");
        actionButtonsArray[0].addActionListener(e -> {
            if (this.hasValidDecimalInputs() && this.hasValidPrecision()) {
                var result = BigDecimalOperand1.add(BigDecimalOperand2).setScale(precisionInInt, RoundingMode.HALF_UP);
                var resultInString = String.valueOf(result.doubleValue());
                currentAction.setText("Current calculation: X + Y");
                if (!resultInString.equals("infinity")) {
                    setResult(resultInString);
                } else {
                    setResult(result.toString());
                }
            }
        });

        //subtract
        actionButtonsArray[1] = new JButton("X - Y");
        actionButtonsArray[1].addActionListener(e -> {
            if (this.hasValidDecimalInputs() && this.hasValidPrecision()) {
                var result = BigDecimalOperand1.subtract(BigDecimalOperand2).setScale(precisionInInt, RoundingMode.HALF_UP);
                var resultInString = String.valueOf(result.doubleValue());
                currentAction.setText("Current calculation: X - Y");
                if (!resultInString.equals("infinity")) {
                    setResult(resultInString);
                } else {
                    setResult(result.toString());
                }
            }
        });

        //multiply
        actionButtonsArray[2] = new JButton("X * Y");
        actionButtonsArray[2].addActionListener(e -> {
            if (this.hasValidDecimalInputs() && this.hasValidPrecision()) {
                var result = BigDecimalOperand1.multiply(BigDecimalOperand2).setScale(precisionInInt, RoundingMode.HALF_UP);
                var resultInString = String.valueOf(result.doubleValue());
                currentAction.setText("Current calculation: X * Y");
                if (!resultInString.equals("infinity")) {
                    setResult(resultInString);
                } else {
                    setResult(result.toString());
                }
            }
        });

        //divide
        actionButtonsArray[3] = new JButton("X / Y");
        actionButtonsArray[3].addActionListener(e -> {
            if (this.hasValidDecimalInputs() && this.hasValidPrecision()) {
                if (BigDecimal.ZERO.compareTo(BigDecimalOperand2) != 0) {
                    var result = BigDecimalOperand1.divide(BigDecimalOperand2, precisionInInt, RoundingMode.HALF_UP);
                    var resultInString = String.valueOf(result.doubleValue());
                    currentAction.setText("Current calculation: X / Y");
                    if (!resultInString.equals("infinity")) {
                        setResult(resultInString);
                    } else {
                        setResult(result.toString());
                    }
                } else {
                    this.setResult("Error: Divide by 0");
                }
            }
        });

        //power
        actionButtonsArray[4] = new JButton("X ^ Y");
        actionButtonsArray[4].addActionListener(e -> {
            if (this.hasValidDecimalInput() && this.hasValidPrecision()) {
                try {
                    var result_text = String.valueOf(BigDecimalOperand1.pow(Integer.parseInt(operand2.getText())).setScale(precisionInInt, RoundingMode.HALF_UP));
                    currentAction.setText("Current calculation: X ^ Y");
                    setResult(result_text);
                    errorMsgForOperand2.setText("");
                } catch (Exception error) {
                    errorMsgForOperand2.setText("Please provide a positive integer as Y value.");
                    setResult("");
                }
            }
        });

        //square root
        actionButtonsArray[5] = new JButton("√X");
        actionButtonsArray[5].addActionListener(e -> {
            if (this.hasValidDecimalInput() && this.hasValidPrecision()) {
                try {
                    var result_text = BigDecimalOperand1.sqrt(new MathContext(precisionInInt, RoundingMode.HALF_UP)).toString();
                    currentAction.setText("Current calculation: √X");
                    setResult(result_text);
                } catch (Exception error) {
                    setResult("");
                    errorMsgForOperand1.setText("Please provide a positive integer as X value.");
                }
            }
        });

        // square
        actionButtonsArray[6] = new JButton("X ^ 2");
        actionButtonsArray[6].addActionListener(e -> {
            if (this.hasValidDecimalInput() && this.hasValidPrecision()) {
                currentAction.setText("Current calculation: X ^ 2");
                setResult(BigDecimalOperand1.pow(2).setScale(precisionInInt, RoundingMode.HALF_UP).toString());
            }
        });

        //factorial
        actionButtonsArray[7] = new JButton("X!");
        actionButtonsArray[7].addActionListener(e -> {
            if (this.hasValidPositiveIntegerInput() && this.hasValidPrecision()) {
                var result = new BigInteger("1");
                while (BigIntegerOperand1.compareTo(BigInteger.ONE) > 0) {
                    result = result.multiply(BigIntegerOperand1);
                    BigIntegerOperand1 = BigIntegerOperand1.subtract(BigInteger.ONE);
                }
                currentAction.setText("Current calculation: X!");
                setResult(result.toString());
            }
        });

        // MOD
        actionButtonsArray[8] = new JButton("MOD");
        actionButtonsArray[8].addActionListener(e -> {
            if (this.hasValidPositiveIntegerInputs() && this.hasValidPrecision()) {
                currentAction.setText("Current calculation: MOD");
                setResult(BigIntegerOperand1.mod(BigIntegerOperand2).toString());
            }
        });

        //GCD
        actionButtonsArray[9] = new JButton("GCD");
        actionButtonsArray[9].addActionListener(e -> {
            if (this.hasValidPositiveIntegerInputs() && this.hasValidPrecision()) {
                currentAction.setText("Current calculation: GCD");
                setResult(BigIntegerOperand1.gcd(BigIntegerOperand2).toString());
            }
        });

        //LCM
        actionButtonsArray[10] = new JButton("LCM");
        actionButtonsArray[10].addActionListener(e -> {
            if (this.hasValidPositiveIntegerInputs() && this.hasValidPrecision()) {
                currentAction.setText("Current calculation: LCM");
                setResult(BigIntegerOperand1.multiply(BigIntegerOperand2.divide(BigIntegerOperand1.gcd(BigIntegerOperand2))).toString());
            }
        });

        // add all buttons to action buttons panel
        for (JButton actionButton : actionButtonsArray) {
            actionButtonsPanel.add(actionButton);
        }

    }

    /**
     * reset primary text fields' contents
     */
    public void reset() {
        super.reset();
        currentAction.setText("Waiting for choosing one.");
        operand1.setText("");
        operand2.setText("");
    }

    /**
     * check if user is providing valid positive integer as precision
     *
     * @return true or false
     */
    private boolean hasValidPrecision() {
        try {
            precisionInInt = Integer.parseInt(precision.getText());
            if (precisionInInt >= 0) {
                errorMsgForPrecision.setText("");
                return true;
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            errorMsgForPrecision.setText("Please provide positive integer as precision.");
            this.updateUI();
        }
        return false;
    }

    /**
     * check if user is providing valid positive integer as operand 1
     *
     * @return true or false
     */
    private boolean hasValidPositiveIntegerInput() {
        errorMsgForOperand2.setText("");
        try {
            BigIntegerOperand1 = new BigInteger(operand1.getText());
            if (BigIntegerOperand1.compareTo(BigInteger.ONE) < 0) {
                throw new RuntimeException();
            }
            errorMsgForOperand1.setText("");
            return true;
        } catch (RuntimeException e) {
            errorMsgForOperand1.setText("Please provide a valid X value.");
            setResult("");
            return false;
        }
    }

    /**
     * check if user is providing valid decimal as operand 1
     *
     * @return true or false
     */
    private boolean hasValidDecimalInput() {
        errorMsgForOperand2.setText("");
        try {
            BigDecimalOperand1 = new BigDecimal(operand1.getText());
            errorMsgForOperand1.setText("");
            return true;
        } catch (RuntimeException e) {
            errorMsgForOperand1.setText("Please provide a valid X value.");
            setResult("");
            return false;
        }
    }

    /**
     * check if user is providing valid positive integers as operand 1 and operand 2
     *
     * @return true or false
     */
    private boolean hasValidPositiveIntegerInputs() {
        try {
            BigIntegerOperand1 = new BigInteger(operand1.getText());
            if (BigIntegerOperand1.compareTo(BigInteger.ONE) < 0) {
                throw new RuntimeException();
            }
        } catch (RuntimeException error) {
            BigIntegerOperand1 = null;
        }
        try {
            BigIntegerOperand2 = new BigInteger(operand2.getText());
            if (BigIntegerOperand2.compareTo(BigInteger.ONE) < 0) {
                throw new RuntimeException();
            }
        } catch (RuntimeException error) {
            BigIntegerOperand2 = null;
        }
        if (BigIntegerOperand1 != null && BigIntegerOperand2 != null) {
            errorMsgForOperand1.setText("");
            errorMsgForOperand2.setText("");
            return true;
        } else {
            errorMsgForOperand1.setText("Please provide a positive integer as X value.");
            errorMsgForOperand2.setText("Please provide a positive integer as Y value.");
            if (BigIntegerOperand1 != null) {
                errorMsgForOperand1.setText("");
            }
            if (BigIntegerOperand2 != null) {
                errorMsgForOperand2.setText("");
            }
            setResult("");
        }
        return false;
    }

    /**
     * check if user is providing valid decimals as operand 1 and operand 2
     *
     * @return true or false
     */
    private boolean hasValidDecimalInputs() {
        try {
            BigDecimalOperand1 = new BigDecimal(operand1.getText());
        } catch (RuntimeException error) {
            BigDecimalOperand1 = null;
        }
        try {
            BigDecimalOperand2 = new BigDecimal(operand2.getText());
        } catch (RuntimeException error) {
            BigDecimalOperand2 = null;
        }
        if (BigDecimalOperand1 != null && BigDecimalOperand2 != null) {
            errorMsgForOperand1.setText("");
            errorMsgForOperand2.setText("");
            return true;
        } else {
            errorMsgForOperand1.setText(BigDecimalOperand1 == null ? "Please provide a valid X value." : "");
            errorMsgForOperand2.setText(BigDecimalOperand2 == null ? "Please provide a valid Y value." : "");
            setResult("");
        }
        return false;
    }
}
