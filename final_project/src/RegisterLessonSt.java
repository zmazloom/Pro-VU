/**
 * Created by ZH on 6/4/2016.
 */
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class RegisterLessonSt extends Student{

    private Button addButton, back;
    private JLabel sentenceAdd1, sentenceAdd2, result;
    private JTextField addLesson, whatProfessor;
    private JPanel registerLessonP;

    public RegisterLessonSt(){
        registerLessonP = new JPanel();
        registerLessonP.setPreferredSize(new Dimension(1400, 700));
        registerLessonP.setBackground(Color.pink);

        sentenceAdd1 = new JLabel("enter id of your lesson that you want register");
        sentenceAdd1.setBounds(550, 200, 200, 30);
        addLesson = new JTextField (10);
        addLesson.setBounds(600, 240, 200, 30);
        sentenceAdd2 = new JLabel("enter username of your lesson that you want register");
        sentenceAdd2.setBounds(550, 280, 200, 30);
        whatProfessor = new JTextField (10);
        whatProfessor.setBounds(600, 320, 200, 30);
        addButton = new Button("REGISTER LESSON");
        addButton.setBounds(625, 380, 150, 30);
        addButton.setActionCommand("REGISTER LESSON");

        result = new JLabel("");
        result.setBounds(600, 340, 200, 30);

        back = new Button("BACK");
        back.setBounds(1250, 20, 100, 40);
        back.setActionCommand("BACK");

        addLesson.addActionListener(new TempListener());
        whatProfessor.addActionListener(new TempListener());
        addButton.addActionListener(new ButtonListener());
        back.addActionListener(new ButtonListener());

        registerLessonP.setLayout(null);
        registerLessonP.add(sentenceAdd1);
        registerLessonP.add(sentenceAdd2);
        registerLessonP.add(whatProfessor);
        registerLessonP.add(addLesson);
        registerLessonP.add(addButton);
        registerLessonP.add(result);
        registerLessonP.add(back);
        add(registerLessonP);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("BACK")) {
                removeAll();
                ChoosePageStudent sp = new ChoosePageStudent();
                add(sp);
                sp.revalidate();
                repaint();
            }
            else {
                TempListener t = new TempListener();
                t.actionPerformed(event);
            }
        }
    }//for button

    private class TempListener implements ActionListener{
        public void actionPerformed(ActionEvent event) {
            int whatResult;
            if ((addLesson.getText()).equals("")==true || (whatProfessor.getText()).equals("")==true) {
                result.setText("empty text field !");
            }
            else {
                try {
                    whatResult = registerLesson((addLesson.getText()), (whatProfessor.getText()));
                } catch (IOException e) {
                    whatResult = -1;
                }
                if (whatResult == 1) {
                    result.setText("you register successfully !");
                    addLesson.setText("");
                    whatProfessor.setText("");
                } else if (whatResult == -1){
                    result.setText("This lesson not exist !");
                    addLesson.setText("");
                    whatProfessor.setText("");
                }
                else {
                    result.setText("You register in this lesson in past !");
                    addLesson.setText("");
                    whatProfessor.setText("");
                }
            }
        }
    }
}