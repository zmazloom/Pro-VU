import java.io.IOException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class CreateLesson extends Professor {

    private JLabel creatSentence, resultCreatLesson, idSentence, nameLessonSentence;
    private JTextField nameLesson, id;
    private Button creat, back;
    private Image imageCreatLesson, lessons;
    private JPanel cLPanel;

    public CreateLesson() {

        try {
            imageCreatLesson = ImageIO.read(getClass().getResource("/image/page.jpg"));
            lessons = ImageIO.read(getClass().getResource("/image/lessons.jpg"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        cLPanel = new JPanel();
        cLPanel.setPreferredSize(new Dimension(1400, 700));
        cLPanel.setBounds(0, 0, 1400, 700);
        cLPanel.setOpaque(false);

        creatSentence = new JLabel("enter name and ID of lesson that you want to creat :");
        creatSentence.setBounds(560, 150, 300, 30);

        nameLesson = new JTextField(50);
        nameLesson.setBounds(600, 250, 200, 30);

        nameLessonSentence = new JLabel("name :");
        nameLessonSentence.setBounds(525, 250, 50, 30);

        id = new JTextField(10);
        id.setBounds(625, 290, 150, 30);

        idSentence = new JLabel("ID :");
        idSentence.setBounds(550, 290, 50, 30);

        resultCreatLesson = new JLabel("");
        resultCreatLesson.setBounds(630, 335, 150, 30);

        creat = new Button("CREAT");
        creat.setBounds(625, 370, 150, 30);
        creat.setActionCommand("CREAT");

        back = new Button("BACK");
        back.setBounds(1250, 20, 100, 40);
        back.setActionCommand("BACK");

        back.addActionListener(new ButtonListenerCreatLesson());
        creat.addActionListener(new ButtonListenerCreatLesson());
        nameLesson.addActionListener(new LabelListenerCreateLesson());
        id.addActionListener(new LabelListenerCreateLesson());

        cLPanel.setLayout(null);
        cLPanel.add(nameLesson);
        cLPanel.add(nameLessonSentence);
        cLPanel.add(creatSentence);
        cLPanel.add(id);
        cLPanel.add(idSentence);
        cLPanel.add(resultCreatLesson);
        cLPanel.add(creat);
        cLPanel.add(back);

        add(cLPanel);
    }

    private class ButtonListenerCreatLesson implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("BACK")) {
                panel.removeAll();
                ChoosePageProfessor chpP = new ChoosePageProfessor();
                panel.add(chpP);
                panel.revalidate();
                panel.repaint();
            } else {
                LabelListenerCreateLesson llcl = new LabelListenerCreateLesson();
                llcl.actionPerformed(event);
            }
        }
    }

    public class LabelListenerCreateLesson implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String lessonName = nameLesson.getText();
            String idS = id.getText();
            boolean result;
            try {
                result = addLesson(lessonName, idS);
            } catch (IOException e) {
                result = false;
            }
            if (result) {
                resultCreatLesson.setText("lesson created successfully!");
                nameLesson.setText("");
                id.setText("");

            } else {
                resultCreatLesson.setText("lesson already exist!");
                nameLesson.setText("");
                id.setText("");
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imageCreatLesson, 0, 0, null);
        g.drawImage(lessons, 520, 20, null);
    }
}