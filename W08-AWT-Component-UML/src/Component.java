import javax.swing.plaf.PanelUI;
import java.awt.*;

class Object {
    protected Object clone() {
        return null;
    }

    boolean equals(Object obj) {
        return false;
    }

    protected void finalize() {
    }

    public int hashCode() {
        return 1;
    }

    public String toString() {
        return null;
    }

}


class Component extends Object {
    boolean isVisible() {
        return false;
    }

    void setVisible(boolean b) {
    }

    void setSize(int width, int height) {
    }

    void setLocation(int x, int y) {
    }

    void setBounds(int x, int y, int width, int height) {
    }

    Dimension getSize() {
        return new Dimension();
    }

    void setSize(Dimension d) {
    }

    void repaint() {
    }

    Dimension getPreferredSize() {
        return new Dimension();
    }

    Color getForeground() {
        return null;
    }

    void setForeground(Color c) {
    }

    Color getBackground() {
        return null;
    }

    void setBackground(Color c) {
    }

    void validate() {
    }

    Font getFont() {
        return null;
    }
}

class Container extends Component {
    void add() {
    }

    void setLayout(LayoutManager m) {
    }

    Component add(Component c) {
        return null;
    }

    Component add(Component c, Object constraints) {
        return null;
    }

    void addNotify() {
    }

    int countComponents() {
        return 1;
    }

    float getAlignmentX() {
        return (float) 0.1;
    }

    float getAlignmentY() {
        return (float) 0.1;
    }
}


class Window extends Container {
    void setLocationByPlatform(boolean b) {
    }

    void pack() {
    }
}

class Frame extends Window {
    boolean isResizable() {
        return false;
    }

    void setResizable(boolean b) {
    }

    String getTitle() {
        return "";
    }

    void setTitle(String s) {
    }
    //Image getIconImage(){return new Image("a");}

    void setIconImage(Image image) {
    }
}

class JFrame extends Frame {
    Component add(Component c) {
        return c;
    }
}


class JComponent extends Container {
    void paintComponent(Graphics g) {
    }

    FontMetrics getFontMetrics(Font f) {
        return null;
    }

    void revalidate() {
    }

    void setFont(Font f) {
    }
}


class JPanel extends JComponent {
    PanelUI getUI() {
        return null;
    }

    void setUI(PanelUI ui) {
    }

    public String getUIClassID() {
        return null;
    }

    protected String paramString() {
        return null;
    }

    void updateUI() {
    }
}