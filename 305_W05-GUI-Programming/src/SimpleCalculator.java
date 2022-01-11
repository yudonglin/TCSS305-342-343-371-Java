// W05-Thu 11/04 Simple Calculator

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class SimpleCalculator {
    public static void main(String[] args) {
        // write your code here
        new GUICalculator(400, 400);
    }
}

class CalculatorTextInputButton extends JButton {

    private static final JTextField textBar;

    static {
        textBar = new JTextField(20);
        textBar.setEditable(false);
    }

    public CalculatorTextInputButton(String text) {
        super(text);
        // implement number button action listeners
        this.addActionListener(e -> textBar.setText(textBar.getText() + this.getText()));
    }

    public static JTextField getTextBar() {
        return textBar;
    }

    public static String getTextBarText() {
        return textBar.getText();
    }

    public static void setTextBarText(String text) {
        textBar.setText(text);
    }

    public static void resetTextBarText() {
        textBar.setText("");
    }
}

class CalculatorOperationButton extends JButton {
    private static final JTextField operationWillPerformTextField = new JTextField();
    private static String firstNum = "NO_NUM";
    private static String operation = "NO_OPERATION";

    public CalculatorOperationButton(String text) {
        super(text);
        this.addActionListener(e -> {
            if (firstNum.equals("NO_NUM") && operation.equals("NO_OPERATION")) {
                operation = this.getText();
                firstNum = CalculatorTextInputButton.getTextBarText();
            } else {
                firstNum = getResult();
                operation = this.getText();
            }
            CalculatorTextInputButton.resetTextBarText();
            operationWillPerformTextField.setText(firstNum + " " + operation);
        });
    }

    public static void reset() {
        operation = "NO_OPERATION";
        firstNum = "NO_NUM";
        operationWillPerformTextField.setText("");
    }

    public static String getResult() {
        if (operation.equals("NO_OPERATION")) {
            return CalculatorTextInputButton.getTextBarText();
        } else if (firstNum.equals("NO_NUM")) {
            return "";
        } else {
            firstNum = switch (operation) {
                case "+" -> String.valueOf(Long.parseLong(firstNum) + Long.parseLong(CalculatorTextInputButton.getTextBarText()));
                case "-" -> String.valueOf(Long.parseLong(firstNum) - Long.parseLong(CalculatorTextInputButton.getTextBarText()));
                case "*" -> String.valueOf(Long.parseLong(firstNum) * Long.parseLong(CalculatorTextInputButton.getTextBarText()));
                case "/" -> String.valueOf(Long.parseLong(firstNum) / Long.parseLong(CalculatorTextInputButton.getTextBarText()));
                default -> String.valueOf(0);
            };
            operation = "NO_OPERATION";
            return firstNum;
        }
    }

    public static JTextField getOperationWillPerformTextField() {
        return operationWillPerformTextField;
    }
}


class GUICalculator extends JFrame {

    public GUICalculator(int width, int height) {

        this.setTitle("Python Calculator");
        // TODO add an icon
        //Frame.setIconImage(Toolkit.getDefaultToolkit().getImage(“meme.jpg”))
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);


        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        this.add(mainPanel);

        // Step 2
        JPanel displayPanel = new JPanel();
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setLayout(new GridLayout(2, 1));
        displayPanel.add(CalculatorOperationButton.getOperationWillPerformTextField());
        displayPanel.add(CalculatorTextInputButton.getTextBar());
        displayPanel.add(CalculatorTextInputButton.getTextBar());
        mainPanel.add(displayPanel);

        // add a layout manager to panel
        JPanel buttonPanel = new JPanel();
        mainPanel.add(buttonPanel);
        buttonPanel.setLayout(new GridLayout(4, 4));

        // number buttons
        for (int i = 0; i < 10; i++) {
            // create number button and add it to button components
            buttonPanel.add(new CalculatorTextInputButton(String.valueOf(i)));
        }

        // operation buttons
        HashMap<String, JButton> operationButtonsArray = new HashMap<>(6);
        operationButtonsArray.put("equal", new JButton("="));
        operationButtonsArray.put("clear", new JButton("C"));

        operationButtonsArray.put("plus", new CalculatorOperationButton("+"));
        operationButtonsArray.put("minus", new CalculatorOperationButton("-"));
        operationButtonsArray.put("multiply", new CalculatorOperationButton("*"));
        operationButtonsArray.put("divide", new CalculatorOperationButton("/"));

        // implement action listeners
        operationButtonsArray.get("clear").addActionListener(e -> {
                    CalculatorTextInputButton.resetTextBarText();
                    CalculatorOperationButton.reset();
                }
        );
        operationButtonsArray.get("equal").addActionListener(e -> {
            CalculatorOperationButton.getOperationWillPerformTextField().setText(
                    CalculatorOperationButton.getOperationWillPerformTextField().getText() + " " + CalculatorTextInputButton.getTextBarText() + " ="
            );
            CalculatorTextInputButton.setTextBarText(CalculatorOperationButton.getResult());
        });

        // Add button components
        for (JButton operationButton : operationButtonsArray.values()) {
            buttonPanel.add(operationButton);
        }
        this.setVisible(true);
    }
}