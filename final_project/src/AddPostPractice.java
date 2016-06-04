import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

public class AddPostPractice extends Professor{

	private Image postsImage;
	private JTextArea tAPost, tAWriterPost, tATopicPost, tAPractice, tAWriterPractice, tATopicPractice, tADataPractice;
	private JLabel practice, post, practiceTopic, practiceWriter, practiceText, practiceData, postTopic, postWriter, postText, result;
	private Button addPractice, addPost, back;
	private String id;
	private JPanel ppPanel;

	public AddPostPractice(String id){
		this.id = id;
		try {
			postsImage = ImageIO.read(getClass().getResource("image/page.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		ppPanel = new JPanel();
		ppPanel.setPreferredSize( new Dimension(1400, 700));
		ppPanel.setBounds(0, 0, 1400, 700);
		ppPanel.setOpaque(false);

		practice = new JLabel("enter your practice :");
		practice.setBounds(200, 100, 150, 30);

		post = new JLabel("enter your post :");
		post.setBounds(800, 100, 150, 30);

		result = new JLabel("");
		result.setBounds(600, 80, 100, 30);

		addPractice = new Button("ADD PRACTICE");
		addPractice.setBounds(400, 550, 150, 30);
		addPractice.setActionCommand("ADD PRACTICE");

		addPost = new Button("ADD POST");
		addPost.setBounds(1000, 550, 150, 30);
		addPost.setActionCommand("ADD POST");

		practiceTopic = new JLabel("topic :");
		practiceTopic.setBounds(200, 130, 50, 30);

		practiceWriter = new JLabel("writer :");
		practiceWriter.setBounds(400, 130, 50, 30);

		practiceText = new JLabel("text :");
		practiceText.setBounds(145, 205, 50, 30);

		practiceData = new JLabel("data :");
		practiceData.setBounds(145, 510, 50, 30);

		postTopic = new JLabel("topic :");
		postTopic.setBounds(800, 130, 50, 30);

		postWriter = new JLabel("writer :");
		postWriter.setBounds(1000, 130, 50, 30);

		postText = new JLabel("text :");
		postText.setBounds(745, 205, 50, 30);

		back = new Button("BACK");
		back.setBounds(1250, 20, 100, 40);
		back.setActionCommand("BACK");

		tAPost = new JTextArea();
		tAWriterPost = new JTextArea();
		tATopicPost = new JTextArea();

		JScrollPane scrollPanePost = new JScrollPane(tAPost);
		scrollPanePost.setBounds(800, 200, 400, 300);
		JScrollPane scrollPaneWriterPost = new JScrollPane(tAWriterPost);
		scrollPaneWriterPost.setBounds(1000, 160, 150, 30);
		JScrollPane scrollPaneTopicPost = new JScrollPane(tATopicPost);
		scrollPaneTopicPost.setBounds(800, 160, 150, 30);
		tAPost.setLineWrap(true);

		tAPractice = new JTextArea();
		tAWriterPractice = new JTextArea();
		tATopicPractice = new JTextArea();
		tADataPractice = new JTextArea();

		JScrollPane scrollPanePractice = new JScrollPane(tAPractice);
		scrollPanePractice.setBounds(200, 200, 400, 300);
		JScrollPane scrollPaneWriterPractice = new JScrollPane(tAWriterPractice);
		scrollPaneWriterPractice.setBounds(400, 160, 150, 30);
		JScrollPane scrollPaneTopicPractice = new JScrollPane(tATopicPractice);
		scrollPaneTopicPractice.setBounds(200, 160, 150, 30);
		JScrollPane scrollPaneDataPractice = new JScrollPane(tADataPractice);
		scrollPaneDataPractice.setBounds(200, 510, 150, 30);
		tAPractice.setLineWrap(true);

		addPost.addActionListener(new ButtonListener());
		addPractice.addActionListener(new ButtonListener());
		back.addActionListener(new ButtonListener());

		ppPanel.setLayout(null);
		ppPanel.add(practice);
		ppPanel.add(post);
		ppPanel.add(addPractice);
		ppPanel.add(addPost);
		ppPanel.add(practiceTopic);
		ppPanel.add(practiceWriter);
		ppPanel.add(practiceText);
		ppPanel.add(practiceData);
		ppPanel.add(postTopic);
		ppPanel.add(postWriter);
		ppPanel.add(postText);
		ppPanel.add(result);
		ppPanel.add(back);
		ppPanel.add(scrollPanePost);
		ppPanel.add(scrollPaneWriterPost);
		ppPanel.add(scrollPaneTopicPost);
		ppPanel.add(scrollPanePractice);
		ppPanel.add(scrollPaneWriterPractice);
		ppPanel.add(scrollPaneTopicPractice);
		ppPanel.add(scrollPaneDataPractice);
		add(ppPanel);

	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals("BACK")) {
				panel.removeAll();
				Lesson l = new Lesson(id);
				panel.add(l);
				panel.revalidate();
				panel.repaint();
			} else if (event.getActionCommand().equals("ADD POST")) {
				String text = tAPost.getText();
				String topic = tATopicPost.getText();
				String writer = tAWriterPost.getText();
				if (text=="" || topic=="" || writer=="") {
					result.setText("empty text field !");
				}
				else {
					try {
						addPost(topic, writer, text, id);
					} catch (IOException e) {
						e.printStackTrace();
					}
					result.setText("post aded successfully !");
					tAPost.setText("");
					tATopicPost.setText("");
					tAWriterPost.setText("");
				}

			}
			if (event.getActionCommand().equals("ADD PRACTICE")) {
				String text = tAPractice.getText();
				String topic = tATopicPractice.getText();
				String writer = tAWriterPractice.getText();
				String data = tADataPractice.getText();
				if (text=="" || topic=="" || writer=="" || data=="") {
					result.setText("empty text field !");
				}
				else {
					try {
						addPractice(topic, writer, text, data, id);
					} catch (IOException e) {
						e.printStackTrace();
					}
					result.setText("practice aded successfully !");
					tAPost.setText("");
					tATopicPost.setText("");
					tAWriterPost.setText("");
					tADataPractice.setText("");
				}
			}
		}
	}//for button

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(postsImage, 0, 0, null);
    }//draw the images

}