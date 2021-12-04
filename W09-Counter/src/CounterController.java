import javax.swing.*;
import java.awt.*;

class CounterController extends JFrame {

    public volatile boolean pauseThread = true;

    public CounterController() {
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(300,150);

        var _counter1 = new Thread(new Counter());
        _counter1.start();
        var _counter2 = new Thread(new Counter2());
        _counter2.start();


        var screen = new JPanel();
        this.add(screen);

        this.setLayout(new GridLayout(1, 2));
        var startButton = new JButton("start");
        var stopButton = new JButton("stop");
        startButton.addActionListener(e -> pauseThread = false);
        stopButton.addActionListener(e -> pauseThread = true);
        screen.add(startButton);
        screen.add(stopButton);

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new CounterController();
    }

    class Counter implements Runnable {

        @Override
        public void run() {
            while (true) {
                while (!pauseThread) {
                    for (int i = 0; i < 10; i++) {
                        System.out.print(i);
                    }
                    System.out.println();
                }
            }
        }
    }

    class Counter2 implements Runnable {

        @Override
        public void run() {
            while (true) {
                while (!pauseThread) {
                    for (int i = 9; i >= 0; i--) {
                        System.out.print(i);
                    }
                    System.out.println();
                }
            }
        }
    }

}

