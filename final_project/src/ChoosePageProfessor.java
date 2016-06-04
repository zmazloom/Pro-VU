import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class ChoosePageProfessor extends Professor {

    private Button change, addLesson, exit;
    private Image choosePageProfessor, professorPage;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    public JPanel chpP;

    public ChoosePageProfessor() {
        try {
            choosePageProfessor = ImageIO.read(getClass().getResource("/image/page.jpg"));
            professorPage = ImageIO.read(getClass().getResource("/image/ProfessorPage.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        chpP = new JPanel();
        chpP.setPreferredSize(new Dimension(1400, 700));
        chpP.setBounds(0, 0, 1400, 700);
        chpP.setOpaque(false);

        //lesson list
        menuBar = new JMenuBar();
        menu = new JMenu("  LESSONS LIST    ");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);
        menuBar.setBounds(630, 360, 110, 50);

        BufferedReader listUsers = null;
        try {
            listUsers = new BufferedReader(new FileReader("lessons" + getUsername() + ".txt"));
            String line;
            while ((line = listUsers.readLine()) != null) {
                String lessonId = listUsers.readLine();
                menuItem = new JMenuItem("name: " + line + " id: " + lessonId, KeyEvent.VK_U);
                menu.add(menuItem);
                menuItem.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        panel.removeAll();
                        Lesson l = new Lesson(lessonId);
                        panel.add(l);
                        panel.revalidate();
                        panel.repaint();
                    }
                });
            }
            listUsers.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        change = new Button("CHANGE USER AND PASS");
        change.setActionCommand("CHANGE");
        change.setBounds(400, 300, 200, 50);

        addLesson = new Button("ADD LESSON");
        addLesson.setActionCommand("ADD LESSON");
        addLesson.setBounds(800, 300, 200, 50);

        exit = new Button("SIGN OUT");
        exit.setActionCommand("SIGN OUT");
        exit.setBounds(1200, 30, 100, 30);

        addLesson.addActionListener(new ButtonListenerChoosePage());
        change.addActionListener(new ButtonListenerChoosePage());
        exit.addActionListener(new ButtonListenerChoosePage());

        chpP.setLayout(null);
        chpP.add(menuBar);
        chpP.add(addLesson);
        chpP.add(change);
        chpP.add(exit);

        add(chpP);
    }

    private class ButtonListenerChoosePage implements ActionListener {
        public void actionPerformed(ActionEvent event) {
          if (event.getActionCommand().equals("CHANGE")) {
                panel.removeAll();
                changePassUser cl = new changePassUser();
                panel.add(cl);
                panel.revalidate();
                panel.repaint();
            } else if (event.getActionCommand().equals("ADD LESSON")) {
                panel.removeAll();
                CreateLesson cl = new CreateLesson();
                panel.add(cl);
                panel.revalidate();
                panel.repaint();
            } else if (event.getActionCommand().equals("SIGN OUT")) {
                try {
                    signOut();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                panel.removeAll();
                Login login = new Login();
                panel.add(login);
                panel.revalidate();
                panel.repaint();
            }
        }
    }//for button


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(choosePageProfessor, 0, 0, null);
        g.drawImage(professorPage, 550, 20, null);
    }//draw the images

}
