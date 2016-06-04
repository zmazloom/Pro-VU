import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class BackGround extends User{

    public static int X_SIZE = 1920;
    public static int Y_SIZE = 1080;
    private Image image;
    public BackGround()  {
        setSize(1400, 700);
        try {
            image = ImageIO.read(new File("/image/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);
    }

}