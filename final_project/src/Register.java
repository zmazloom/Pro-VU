import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;

public class Register extends User {

    private JLabel wrongLabel, sentence, name, family, user, pass, passAgain, sOrP, exit;
    private Button register, back;
    private JTextField nameJT, familyJT, userJT, passJT, passAgainJT;
    private JRadioButton st, pr;
    private Image imageRegister, registerPage;
    public JPanel registerPanel;

    public Register() {
        try {
            imageRegister = ImageIO.read(getClass().getResource("/image/background.jpg"));
            registerPage = ImageIO.read(getClass().getResource("/image/registerPage.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        registerPanel = new JPanel();
        registerPanel.setPreferredSize(new Dimension(1400, 800));
        registerPanel.setBounds(0, 0, 1400, 800);
        registerPanel.setOpaque(false);

        wrongLabel = new JLabel("");
        wrongLabel.setBounds(500, 60, 200, 40);

        sentence = new JLabel("fill all of this text fields and register!");
        sentence.setBounds(550, 100, 200, 40);

        name = new JLabel("enter your name :");
        name.setBounds(550, 140, 200, 30);

        nameJT = new JTextField(20);
        nameJT.setBounds(550, 180, 200, 30);

        family = new JLabel("enter your family :");
        family.setBounds(550, 220, 200, 30);

        familyJT = new JTextField(20);
        familyJT.setBounds(550, 260, 200, 30);

        user = new JLabel("enter a username :");
        user.setBounds(550, 300, 200, 30);

        userJT = new JTextField(20);
        userJT.setBounds(550, 340, 200, 30);

        pass = new JLabel("enter your password :");
        pass.setBounds(550, 380, 200, 30);

        passJT = new JTextField(20);
        passJT.setBounds(550, 420, 200, 30);

        passAgain = new JLabel("enter your password again :");
        passAgain.setBounds(550, 460, 200, 30);

        passAgainJT = new JTextField(20);
        passAgainJT.setBounds(550, 500, 200, 30);

        sOrP = new JLabel("Are you student or professor ?");
        sOrP.setBounds(550, 540, 200, 30);

        back = new Button("LOGIN PAGE");
        back.setBounds(1210, 20, 150, 50);
        back.setActionCommand("back");

        register = new Button("REGISTER");
        register.setBounds(550, 620, 200, 30);


        st = new JRadioButton("student");
        st.setBounds(550, 580, 100, 30);
        st.setBackground(Color.orange);
        pr = new JRadioButton("professor");
        pr.setBounds(650, 580, 100, 30);
        pr.setBackground(Color.orange);
        exit = new JLabel("");
        exit.setBounds(0, 0, 100, 30);

        ButtonGroup group = new ButtonGroup();
        group.add(st);
        group.add(pr);

        nameJT.addActionListener(new TempListener());
        familyJT.addActionListener(new TempListener());
        userJT.addActionListener(new TempListener());
        passJT.addActionListener(new TempListener());
        passAgainJT.addActionListener(new TempListener());
        register.addActionListener(new ButtonListener());
        st.addActionListener(new QuoteListener());
        pr.addActionListener(new QuoteListener());
        back.addActionListener(new ButtonListener());

        registerPanel.setLayout(null);
        registerPanel.add(wrongLabel);
        registerPanel.add(sentence);
        registerPanel.add(name);
        registerPanel.add(nameJT);
        registerPanel.add(family);
        registerPanel.add(familyJT);
        registerPanel.add(user);
        registerPanel.add(userJT);
        registerPanel.add(pass);
        registerPanel.add(passJT);
        registerPanel.add(passAgain);
        registerPanel.add(passAgainJT);
        registerPanel.add(sOrP);
        registerPanel.add(st);
        registerPanel.add(pr);
        registerPanel.add(register);
        registerPanel.add(back);

        add(registerPanel);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("back")) {
                panel.removeAll();
                Login l = new Login();
                panel.add(l);
                panel.revalidate();
                panel.repaint();
            } else {
                TempListener t = new TempListener();
                t.actionPerformed(event);
            }
        }
    }//for button

    private class QuoteListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object source = event.getSource();
            if (source == st) exit.setText("student");
            else if (source == pr) exit.setText("professor");
            else exit.setText("");
        }
    }//for radio button

    private class TempListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            String nameJTStr, familyJTStr, userJTStr, passJTStr, passAgainJTStr, radioSOrPr;
            boolean result;

            nameJTStr = nameJT.getText();
            familyJTStr = familyJT.getText();
            userJTStr = userJT.getText();
            passJTStr = passJT.getText();
            passAgainJTStr = passAgainJT.getText();
            radioSOrPr = exit.getText();

            if (nameJTStr.equals("") || familyJTStr.equals("") || userJTStr.equals("") || passJTStr.equals("") || passAgainJTStr.equals("") || radioSOrPr.equals("")) {
                wrongLabel.setText("empty text field!");
            } else if (!passJTStr.equals(passAgainJTStr)) {
                wrongLabel.setText("wrong password!");
            } else {
                try {
                    result = register(nameJTStr, familyJTStr, userJTStr, passJTStr, radioSOrPr);
                } catch (IOException ex) {
                    result = false;
                }

                if (result) {
                    wrongLabel.setText("");
                    panel.removeAll();
                    Login l = new Login();
                    panel.add(l);
                    panel.revalidate();
                    panel.repaint();
                } else {
                    wrongLabel.setText("repetitive username !");
                }
            }
        }
    }//for jtext fields

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageRegister, 0, 0, null);
        g.drawImage(registerPage, 450, 20, null);
    }//draw the images

}