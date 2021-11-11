/*
Note:
This file is distributed under the GPL-3.0 License
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.util.Objects;

public class SimpleCalculator {
    public static void main(String[] args) throws IOException {
        // write your code here
        new GUICalculator(400, 200);
    }
}

class NormalTextLine extends JTextField {
    public NormalTextLine(String text) {
        super(text);
        this.setEditable(false);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder());
    }
}

class HelpFrame extends JFrame {

    public HelpFrame(int width, int height) throws IOException {
        this.setSize(width, height);
        this.setTitle("Help");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 1));
        mainPanel.add(new NormalTextLine("Plus"));
        mainPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File(String.valueOf(FileSystems.getDefault().getPath("W06-GU-Design/ex_plus.png").toAbsolutePath()))))));
        mainPanel.add(new NormalTextLine("Minus"));
        mainPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File(String.valueOf(FileSystems.getDefault().getPath("W06-GU-Design/ex_minus.png").toAbsolutePath()))))));
        mainPanel.add(new NormalTextLine("Multiply"));
        mainPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File(String.valueOf(FileSystems.getDefault().getPath("W06-GU-Design/ex_multiply.png").toAbsolutePath()))))));
        mainPanel.add(new NormalTextLine("Divide"));
        mainPanel.add(new JLabel(new ImageIcon(ImageIO.read(new File(String.valueOf(FileSystems.getDefault().getPath("W06-GU-Design/ex_divide.png").toAbsolutePath()))))));
        var scrollPane = new JScrollPane(mainPanel);
        this.add(scrollPane);
    }
}

class AboutFrame extends JFrame {

    public AboutFrame(int width, int height) {
        this.setSize(width, height);
        this.setTitle("About");
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1));
        mainPanel.add(new NormalTextLine("Version: 0.0.dev0"));
        mainPanel.add(new NormalTextLine("About the author of this program:"));
        mainPanel.add(new NormalTextLine("Yudong Lin - A student at uwt"));
        this.add(mainPanel);
    }
}

class GUICalculator extends JFrame {

    JTextField operand1 = new JTextField("");
    JTextField operand2 = new JTextField("");
    JTextField calResult = new JTextField("");
    JComboBox<String> operationComboBox = new JComboBox<>();

    public GUICalculator(int width, int height) throws IOException {

        this.setTitle("Python Calculator");
        // TODO add an icon
        //Frame.setIconImage(Toolkit.getDefaultToolkit().getImage(“meme.jpg”))
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width, height);

        // add menu
        var menuBar = new JMenuBar();
        var calculatorMenu = new JMenu("Calculator");
        var startMenuItem = new JMenuItem("Start");
        var exitMenuItem = new JMenuItem("Exit");

        calculatorMenu.add(startMenuItem);
        calculatorMenu.add(exitMenuItem);
        menuBar.add(calculatorMenu);

        var helpMenu = new JMenu("Help");
        var helpMenuItem = new JMenuItem("Help");
        var helpFrame = new HelpFrame(800, 800);
        helpMenuItem.addActionListener(e -> helpFrame.setVisible(true));
        helpMenu.add(helpMenuItem);

        var aboutMenu = new JMenu("About");
        var aboutMenuItem = new JMenuItem("About");
        var aboutFrame = new AboutFrame(400, 200);
        aboutMenuItem.addActionListener(e -> aboutFrame.setVisible(true));
        aboutMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);
        menuBar.add(aboutMenu);

        this.setJMenuBar(menuBar);

        //init main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.setVisible(false);
        this.add(mainPanel);

        startMenuItem.addActionListener(e -> mainPanel.setVisible(true));
        exitMenuItem.addActionListener(e -> {
            reset();
            mainPanel.setVisible(false);
        });

        // Step 2
        JPanel displayPanel = new JPanel();
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setLayout(new GridLayout(1, 5));

        operationComboBox.addItem("+");
        operationComboBox.addItem("-");
        operationComboBox.addItem("*");
        operationComboBox.addItem("/");

        displayPanel.add(operand1);
        displayPanel.add(operationComboBox);
        displayPanel.add(operand2);

        var equalButton = new JButton("=");
        equalButton.addActionListener(e -> {
            var num1 = Long.parseLong(this.operand1.getText());
            var num2 = Long.parseLong(this.operand2.getText());
            switch (Objects.requireNonNull(operationComboBox.getSelectedItem()).toString()) {
                case "+" -> this.calResult.setText(String.valueOf(num1 + num2));
                case "-" -> this.calResult.setText(String.valueOf(num1 - num2));
                case "*" -> this.calResult.setText(String.valueOf(num1 * num2));
                case "/" -> this.calResult.setText(num2 != 0 ? String.valueOf(num1 / num2) : "ERROR: Divide by 0");
                default -> this.calResult.setText("ERROR");
            }
        });

        calResult.setEditable(false);
        displayPanel.add(equalButton);
        displayPanel.add(calResult);
        mainPanel.add(displayPanel);

        // add clear button
        var clearButton = new JButton("C");
        clearButton.addActionListener(e -> reset());
        mainPanel.add(clearButton);

        // show menu
        this.setVisible(true);
    }

    private void reset() {
        this.operand1.setText("");
        this.operand2.setText("");
        this.calResult.setText("");
    }
}