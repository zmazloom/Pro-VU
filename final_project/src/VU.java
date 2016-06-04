import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class VU extends JPanel {
    static JPanel panel;
    public static void main(String[] args) {

        JFrame frame = new JFrame("Pro-VU");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        Register g = new Register();
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(1400, 800));
        panel.add(g);
        frame.setPreferredSize(new Dimension(800, 500));
        frame.pack();
        frame.setVisible(true);
        frame.add(panel);

    }
}