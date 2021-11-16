import javax.swing.*;

class NormalTextLine extends JTextField {

    /**
     * @param text the content of this text field
     */
    public NormalTextLine(String text) {
        super(text);
        this.setEditable(false);
        this.setBorder(BorderFactory.createEmptyBorder());
    }
}
