import javax.swing.*;

class NormalTextLine extends JTextField {
    public NormalTextLine(String text) {
        super(text);
        this.setEditable(false);
        this.setBorder(BorderFactory.createEmptyBorder());
    }
}
