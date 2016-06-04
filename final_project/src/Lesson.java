import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

/**
 * Created by ZH on 6/4/2016.
 */
public class Lesson extends Professor {
    private Button addRemove, posts, seeAnswers, edition, back;
    public JPanel lPanel;
    private JLabel postLabel, practiceLabel, studentLabel;
    private String id;
    private Image lessonImage;

    public Lesson(String id) {
        this.id = id;

        try {
            lessonImage = ImageIO.read(getClass().getResource("/image/page.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        lPanel = new JPanel();
        lPanel.setPreferredSize(new Dimension(1400, 700));
        lPanel.setBounds(0, 0, 1400, 700);
        lPanel.setOpaque(false);

        practiceLabel = new JLabel("practices :");
        practiceLabel.setBounds(120, 160, 100, 30);

        postLabel = new JLabel("post :");
        postLabel.setBounds(410, 160, 100, 30);

        studentLabel = new JLabel("username of students :");
        studentLabel.setBounds(750, 160, 150, 30);

        addRemove = new Button("Add OR REMOVE STUDENT");
        addRemove.setActionCommand("Add OR REMOVE");
        addRemove.setBounds(150, 20, 200, 50);

        posts = new Button("ADD POSTS AND PRACTICES");
        posts.setActionCommand("ADD POSTS AND PRACTICES");
        posts.setBounds(400, 20, 200, 50);

        seeAnswers = new Button("ANSWERS OF PRACTICES");
        seeAnswers.setActionCommand("ANSWERS");
        seeAnswers.setBounds(650, 20, 200, 50);

        edition = new Button("EDITION POSTS");
        edition.setActionCommand("EDITION");
        edition.setBounds(900, 20, 150, 50);

        back = new Button("BACK");
        back.setBounds(1250, 20, 100, 40);
        back.setActionCommand("BACK");

        DefaultListModel studentList = new DefaultListModel();
        BufferedReader listUsers = null;
        try {
            listUsers = new BufferedReader(new FileReader(id + getUsername() + "studentname.txt"));
            String line;
            while ((line = listUsers.readLine()) != null) {
                studentList.addElement(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JList mainList = new JList(studentList);
        mainList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mainList.setSelectedIndex(0);
        mainList.setVisibleRowCount(3);
        JScrollPane studentListScrollPane = new JScrollPane(mainList);
        studentListScrollPane.setBounds(120, 200, 250, 300);

        //list post
        DefaultListModel postList = new DefaultListModel();
        BufferedReader listPosts = null;
        String topicPost="", writerPost="", textPost="";
        try {
            listPosts = new BufferedReader(new FileReader(id + getUsername() + "post.txt"));
            String line;
            while ((line = listPosts.readLine()) != null) {
                while(!line.equals("...!")) {
                    topicPost += line;
                    line = listPosts.readLine();
                }
                line = listPosts.readLine();
                while(!line.equals("...!")) {
                    writerPost += line;
                    line = listPosts.readLine();
                }
                line = listPosts.readLine();
                while(!line.equals("...!")) {
                    textPost += line;
                    line = listPosts.readLine();
                }

                postList.addElement("topic : " + topicPost);
                postList.addElement("writer : " + writerPost);
                postList.addElement(textPost);
                postList.addElement("-------------------------------");
            }
            listPosts.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JList pList = new JList(postList);
        pList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        pList.setSelectedIndex(0);
        pList.setVisibleRowCount(3);
        JScrollPane postListScrollPane = new JScrollPane(pList);
        postListScrollPane .setBounds(410, 200, 300, 300);

        //list practice
        DefaultListModel practiceList = new DefaultListModel();
        BufferedReader listPractices = null;
        String topicPractice="", writerPractice="", textPractice="", dataPractice="";
        try {
            listPractices = new BufferedReader(new FileReader(id + getUsername() + "practice.txt"));
            String line;
            while ((line = listPractices.readLine()) != null) {
                while(!line.equals("...!")) {
                    topicPractice += line;
                    line = listPractices.readLine();
                }
                line = listPractices.readLine();
                while(!line.equals("...!")) {
                    writerPractice += line;
                    line = listPractices.readLine();
                }
                line = listPractices.readLine();
                while(!line.equals("...!")) {
                    textPractice += line;
                    line = listPractices.readLine();
                }
                line = listPractices.readLine();
                while(!line.equals("...!")) {
                    dataPractice += line;
                    line = listPractices.readLine();
                }

                practiceList.addElement("topic : " + topicPractice);
                practiceList.addElement("writer : " + writerPractice);
                practiceList.addElement("while : " + dataPractice);
                practiceList.addElement(textPractice);
                practiceList.addElement("-------------------------------");
            }
            listPractices.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        JList prList = new JList(practiceList);
        prList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        prList.setSelectedIndex(0);
        prList.setVisibleRowCount(3);
        JScrollPane practiceListScrollPane = new JScrollPane(prList);
        practiceListScrollPane .setBounds(750, 200, 300, 300);

        //list practice
        addRemove.addActionListener(new ButtonListener());
        back.addActionListener(new ButtonListener());
        posts.addActionListener(new ButtonListener());
        seeAnswers.addActionListener(new ButtonListener());
        edition.addActionListener(new ButtonListener());

        lPanel.setLayout(null);
        lPanel.add(addRemove);
        lPanel.add(posts);
        lPanel.add(seeAnswers);
        lPanel.add(edition);
        lPanel.add(studentListScrollPane);
        lPanel.add(postListScrollPane);
        lPanel.add(practiceListScrollPane);
        lPanel.add(practiceLabel);
        lPanel.add(postLabel);
        lPanel.add(studentLabel);
        lPanel.add(back);

        add(lPanel);
    }


    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            if (event.getActionCommand().equals("STUDENTS")) {

            } else if (event.getActionCommand().equals("ADD POSTS AND PRACTICES")) {
                panel.removeAll();
                AddPostPractice app = new AddPostPractice(id);
                panel.add(app);
                panel.revalidate();
                panel.repaint();
            } else if (event.getActionCommand().equals("ANSWERS")) {
            } else if (event.getActionCommand().equals("Add OR REMOVE")) {
                panel.removeAll();
                AddRemoveStudent ars = new AddRemoveStudent(id);
                panel.add(ars);
                panel.revalidate();
                panel.repaint();
            } else if (event.getActionCommand().equals("BACK")) {
                panel.removeAll();
                ChoosePageProfessor chpP = new ChoosePageProfessor();
                panel.add(chpP);
                panel.revalidate();
                panel.repaint();
            }
        }
    }//for button

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(lessonImage, 0, 0, null);
    }//draw the images
}
