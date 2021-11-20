import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.nio.file.FileSystems;

/**
 * A frame that contains a panel with drawings.
 */
class DrawFrame extends JFrame {

    public DrawFrame() {
        this.setLayout(new FlowLayout());

        var screen = new DrawComponent();
        this.add(screen);

        var buttonPanels = new JPanel();
        this.add(buttonPanels);

        var upButton = new Button("up");
        upButton.addActionListener(e -> {
            SpaceShip.addY(-10);
            screen.repaint();
        });
        buttonPanels.add(upButton);
        var downButton = new Button("down");
        downButton.addActionListener(e -> {
            SpaceShip.addY(10);
            screen.repaint();
        });
        buttonPanels.add(downButton);

        var leftButton = new Button("left");
        leftButton.addActionListener(e -> {
            SpaceShip.addX(-10);
            screen.repaint();
        });
        buttonPanels.add(leftButton);


        var rightButton = new Button("right");
        rightButton.addActionListener(e -> {
            SpaceShip.addX(10);
            screen.repaint();
        });
        buttonPanels.add(rightButton);

        pack();
    }

    /**
     * @author Cay Horstmann
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() ->
        {
            var frame = new DrawFrame();
            frame.setTitle("DrawTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class SpaceShip {

    private static final Image _image = new ImageIcon(FileSystems.getDefault().getPath("W07-Graphics-Programming/src/spaceship_icon.png").toAbsolutePath().toString()).getImage();

    private static int x, y = 0;

    public static void addX(int _x) {
        x += _x;
    }

    public static void addY(int _y) {
        y += _y;
    }

    public void draw(Graphics2D surface) {
        surface.drawImage(_image, x, y, null);
    }
}

/**
 * A component that displays rectangles and ellipses.
 */
class DrawComponent extends JComponent {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;
    private static final SpaceShip spaceship = new SpaceShip();
    int leftX = 100;
    int topY = 100;

    public void paintComponent(Graphics g) {

        var g2 = (Graphics2D) g;

        // draw a rectangle

        double width = 200;
        double height = 150;

        var rect = new Rectangle2D.Double(leftX, topY, width, height);
        g2.draw(rect);

        g2.setColor(Color.CYAN);

        // draw the enclosed ellipse
        var ellipse = new Ellipse2D.Double();
        ellipse.setFrame(rect);
        g2.draw(ellipse);

        g2.setColor(Color.RED);

        // draw a diagonal line
        g2.draw(new Line2D.Double(leftX, topY, leftX + width, topY + height));

        // draw a circle with the same center
        g2.setColor(Color.BLUE);
        double centerX = rect.getCenterX();
        double centerY = rect.getCenterY();
        double radius = 150;

        var circle = new Ellipse2D.Double();
        circle.setFrameFromCenter(centerX, centerY, centerX + radius, centerY + radius);
        g2.draw(circle);

        g2.setFont(new Font("SanSerif", Font.BOLD + Font.ITALIC, 20));

        spaceship.draw(g2);

        g2.drawString("DrawTest", 50, 100);
    }

    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}


