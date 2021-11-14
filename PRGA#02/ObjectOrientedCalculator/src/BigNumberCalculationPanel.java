import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

public class BigNumberCalculationPanel extends CalculatorPanel {

    private final JTextField operand1 = new JTextField(10);
    private final JTextField operand2 = new JTextField(10);
    private final NormalTextLine errorMsgForOperand1 = new NormalTextLine("");
    private final NormalTextLine errorMsgForOperand2 = new NormalTextLine("");
    private BigDecimal BigDecimalOperand1;
    private BigDecimal BigDecimalOperand2;

    public BigNumberCalculationPanel() {
        super("Big Number Calculator");
        this.setLayout(new GridLayout(8, 1));

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
        JTextField precision = new JTextField(5);
        precision.setText("20");
        bottomPanel.add(precision);
        bottomPanel.add(new NormalTextLine("digits after the decimal place in the result:"));

        var resultPanel = new JPanel();
        resultPanel.add(new NormalTextLine("Result:"));
        resultPanel.add(result);
        this.add(resultPanel);

        var actionDesPanel = new JPanel();
        actionDesPanel.add(new NormalTextLine("Click the buttons below to calculate"));
        this.add(actionDesPanel);


        // add buttons

        var actionButtonsPanel = new JPanel();
        this.add(actionButtonsPanel);

        HashMap<String, JButton> actionButtonsMap = new HashMap<>();

        actionButtonsMap.put("add", new JButton("X + Y"));
        actionButtonsMap.get("add").addActionListener(e -> {
            if (this.hasValidInputs()) {
                setResult(BigDecimalOperand1.add(BigDecimalOperand2).toString());
            }
        });

        actionButtonsMap.put("subtract", new JButton("X - Y"));
        actionButtonsMap.get("subtract").addActionListener(e -> {
            if (this.hasValidInputs()) {
                setResult(BigDecimalOperand1.subtract(BigDecimalOperand2).toString());
            }
        });

        actionButtonsMap.put("multiply", new JButton("X * Y"));
        actionButtonsMap.get("multiply").addActionListener(e -> {
            if (this.hasValidInputs()) {
                setResult(BigDecimalOperand1.multiply(BigDecimalOperand2).toString());
            }
        });

        actionButtonsMap.put("divide", new JButton("X / Y"));
        actionButtonsMap.get("divide").addActionListener(e -> {
            if (this.hasValidInputs()) {
                setResult(BigDecimalOperand1.divide(BigDecimalOperand2, Integer.parseInt(precision.getText()), RoundingMode.HALF_UP).toString());
            }
        });

        actionButtonsMap.put("power", new JButton("X ^ Y"));
        actionButtonsMap.get("power").addActionListener(e -> {
            if (this.hasValidInputs()) {
                setResult(String.valueOf(Math.pow(BigDecimalOperand1.doubleValue(), BigDecimalOperand2.doubleValue())));
            }
        });

        actionButtonsMap.put("squareRootX", new JButton("√X"));
        actionButtonsMap.get("squareRootX").addActionListener(e -> {
            if (this.hasValidInput()) {
                setResult(BigDecimalOperand1.sqrt(new MathContext(Integer.parseInt(precision.getText()), RoundingMode.HALF_UP)).toString());
            }
        });

        actionButtonsMap.put("squareX", new JButton("X ^ 2"));
        actionButtonsMap.get("squareX").addActionListener(e -> {
            if (this.hasValidInput()) {
                setResult(BigDecimalOperand1.pow(2).toString());
            }
        });

        actionButtonsMap.put("xFactorial", new JButton("X!"));
        actionButtonsMap.get("xFactorial").addActionListener(e -> {
            var num1 = new BigInteger(operand1.getText());
            var result = new BigInteger("1");
            while (num1.compareTo(BigInteger.ONE) > 0) {
                result = result.multiply(num1);
                num1 = num1.subtract(BigInteger.ONE);
            }
            setResult(result.toString());
        });

        actionButtonsMap.put("MOD", new JButton("MOD"));
        actionButtonsMap.get("MOD").addActionListener(e -> {
            var num1 = new BigInteger(operand1.getText());
            var num2 = new BigInteger(operand2.getText());
            setResult(num1.mod(num2).toString());
        });

        actionButtonsMap.put("GCD", new JButton("GCD"));
        actionButtonsMap.get("GCD").addActionListener(e -> {
            var num1 = new BigInteger(operand1.getText());
            var num2 = new BigInteger(operand2.getText());
            setResult(num1.gcd(num2).toString());
        });

        actionButtonsMap.put("LCM", new JButton("LCM"));
        actionButtonsMap.get("LCM").addActionListener(e -> {
            var num1 = new BigInteger(operand1.getText());
            var num2 = new BigInteger(operand2.getText());
            setResult(num1.multiply(num2.divide(num1.gcd(num2))).toString());
        });

        for (JButton actionButton : actionButtonsMap.values()) {
            actionButtonsPanel.add(actionButton);
        }

    }

    public void reset() {
        super.reset();
        operand1.setText("");
        operand2.setText("");
    }

    private boolean hasValidInput() {
        try {
            errorMsgForOperand2.setText("");
            BigDecimalOperand1 = new BigDecimal(operand1.getText());
            errorMsgForOperand1.setText("");
            return true;
        } catch (Exception e) {
            errorMsgForOperand1.setText("Please provide a valid X value.");
            setResult("");
            return false;
        }
    }

    private boolean hasValidInputs() {
        try {
            BigDecimalOperand1 = new BigDecimal(operand1.getText());
        } catch (Exception error) {
            BigDecimalOperand1 = null;
        }
        try {
            BigDecimalOperand2 = new BigDecimal(operand2.getText());
        } catch (Exception error) {
            BigDecimalOperand2 = null;
        }
        if (BigDecimalOperand1 != null && BigDecimalOperand2 != null) {
            errorMsgForOperand1.setText("");
            errorMsgForOperand2.setText("");
            return true;
        } else {
            errorMsgForOperand1.setText("Please provide a valid X value.");
            errorMsgForOperand2.setText("Please provide a valid Y value.");
            if (BigDecimalOperand1 != null) {
                errorMsgForOperand1.setText("");
            }
            if (BigDecimalOperand2 != null) {
                errorMsgForOperand2.setText("");
            }
            setResult("");
        }
        return false;
    }
}
