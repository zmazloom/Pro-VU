/**
 * Created by ZH on 6/4/2016.
 */
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class AddRemoveStudent extends Professor{

    private Button addButton, removeButton, back;
    private JLabel sentenceRemove, sentenceAdd, result;
    private JTextField addS, removeS;
    private JPanel addRemovePanel;
    private String id;
    private Image lessonImage;

    public AddRemoveStudent(String id){
        this.id = id;

        try {
            lessonImage = ImageIO.read(getClass().getResource("/image/page.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addRemovePanel = new JPanel();
        addRemovePanel.setPreferredSize(new Dimension(1400, 700));
        addRemovePanel.setBounds(0, 0, 1400, 700);
        addRemovePanel.setOpaque(false);

        sentenceAdd = new JLabel("enter username of your student for adding:");
        sentenceAdd.setBounds(550, 200, 200, 30);
        addS = new JTextField (10);
        addS.setBounds(600, 240, 200, 30);
        addButton = new Button("ADD STUDENT");
        addButton.setBounds(625, 280, 150, 30);
        addButton.setActionCommand("ADD STUDENT");

        sentenceRemove = new JLabel("enter username of your student for removing :");
        sentenceRemove.setBounds(550, 320, 200, 30);
        removeS = new JTextField (10);
        removeS.setBounds(600, 360, 200, 30);
        removeButton = new Button("REMOVE STUDENT");
        removeButton.setBounds(625, 450, 150, 30);
        removeButton.setActionCommand("REMOVE STUDENT");

        result = new JLabel("");
        result.setBounds(600, 400, 200, 30);

        back = new Button("BACK");
        back.setBounds(1250, 20, 100, 40);
        back.setActionCommand("BACK");

        addS.addActionListener(new TempListener());
        addButton.addActionListener(new ButtonListener());
        removeS.addActionListener(new TempListener());
        removeButton.addActionListener(new ButtonListener());
        back.addActionListener(new ButtonListener());

        addRemovePanel.setLayout(null);
        addRemovePanel.add(sentenceAdd);
        addRemovePanel.add(addS);
        addRemovePanel.add(addButton);
        addRemovePanel.add(sentenceRemove);
        addRemovePanel.add(removeS);
        addRemovePanel.add(removeButton);
        addRemovePanel.add(back);
        addRemovePanel.add(result);
        add(addRemovePanel);
    }

    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
           if (event.getActionCommand().equals("BACK")) {
               panel.removeAll();
               Lesson l = new Lesson(id);
               panel.add(l);
               panel.revalidate();
               panel.repaint();
           }
            else {
                TempListener t = new TempListener();
                t.actionPerformed(event);
            }
        }
    }//for button

    private class TempListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if ((addS.getText()).equals("")==false) {
                try {
                    boolean whatResult = addStudent((addS.getText()), id);
                    if (whatResult == false) {
                        result.setText("Student exists !");
                    }
                    else {
                        result.setText("successfully adding !");
                        addS.setText("");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addS.setText("");
            }
            if ((removeS.getText()).equals("")==false) {
                try {
                    boolean whatResult = removeStudent((removeS.getText()), id);
                    if (whatResult == false) {
                        result.setText("Student not exist !");
                    }
                    else {
                        result.setText("successfully removing !");
                        removeS.setText("");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                removeS.setText("");
            }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(lessonImage, 0, 0, null);
    }//draw the images
}
