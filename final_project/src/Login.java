import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Login extends User {

    private JLabel userLabel, passLabel, wrongLabel;
    private Button enterButton, back;
    private JTextField userText;
    private JPasswordField pass;
    private Image imageLogin, loginPage;
    private JPanel loginPanel;

    public Login() {
        try {
            imageLogin = ImageIO.read(getClass().getResource("/image/background.jpg"));
            loginPage = ImageIO.read(getClass().getResource("/image/loginPage.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(1400, 700));
        loginPanel.setBounds(0, 0, 1400, 700);
        loginPanel.setOpaque(false);

        wrongLabel = new JLabel("");
        wrongLabel.setBounds(550, 200, 200, 30);

        userLabel = new JLabel("Enter your username :");
        userLabel.setBounds(600, 240, 200, 30);

        userText = new JTextField(10);
        userText.setBounds(600, 280, 200, 30);

        passLabel = new JLabel("Enter your password :");
        passLabel.setBounds(600, 320, 200, 30);

        pass = new JPasswordField();
        pass.setBounds(600, 360, 200, 30);

        enterButton = new Button("Enter");
        enterButton.setBounds(650, 400, 100, 30);

        back = new Button("REGISTER PAGE");
        back.setBounds(1210, 20, 150, 50);
        back.setActionCommand("back");

        back.addActionListener(new ButtonListener());
        userText.addActionListener(new TempListener());
        pass.addActionListener(new TempListener());
        enterButton.addActionListener(new ButtonListener());

        loginPanel.setLayout(null);
        loginPanel.add(wrongLabel);
        loginPanel.add(userLabel);
        loginPanel.add(userText);
        loginPanel.add(passLabel);
        loginPanel.add(pass);
        loginPanel.add(enterButton);
        loginPanel.add(back);

        add(loginPanel);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("back")) {
                panel.removeAll();
                Register r = new Register();
                panel.add(r);
                panel.revalidate();
                panel.repaint();
            } else {
                TempListener t = new TempListener();
                t.actionPerformed(event);
            }
        }
    }//for button

    private class TempListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            String userStr, passStr;
            int result;

            userStr = userText.getText();
            passStr = pass.getText();

            if (userStr.equals("") || passStr.equals("")) wrongLabel.setText("empty text field!");
            else {
                try {
                    result = login(userStr, passStr);
                } catch (IOException e) {
                    result = 0;
                }
                if (result == 1) {
                    wrongLabel.setText("");
                    panel.removeAll();
                    ChoosePageProfessor chpP = new ChoosePageProfessor();
                    panel.add(chpP);
                    panel.revalidate();
                    panel.repaint();
                } else if (result == 2) {
                    wrongLabel.setText("");
                    panel.removeAll();
                    ChoosePageStudent chpS = new ChoosePageStudent();
                    panel.add(chpS);
                    panel.revalidate();
                    panel.repaint();
                } else {
                    wrongLabel.setText("Wrong username or password!");
                }
            }

        }
    }//for jtext fields

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageLogin, 0, 0, null);
        g.drawImage(loginPage, 450, 30, null);
    }//draw the images

}