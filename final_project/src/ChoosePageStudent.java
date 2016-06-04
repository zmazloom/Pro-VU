import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class ChoosePageStudent extends Professor {

	private Button change, registerLesson, exit;
	private Image StudentPage, stPage;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	public JPanel sP;

	public ChoosePageStudent() {
		try {
			StudentPage = ImageIO.read(getClass().getResource("/image/page.jpg"));
			stPage = ImageIO.read(getClass().getResource("/image/StudentPage.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		sP = new JPanel();
		sP.setPreferredSize(new Dimension(1400, 700));
		sP.setOpaque(false);

		//lesson list
		menuBar = new JMenuBar();
		menu = new JMenu("lessons");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(menu);
		menuBar.setBounds(425, 300, 150, 50);

		/*BufferedReader lessons = null;
		try {
			listUsers = new BufferedReader(new FileReader("lessons" + getUsername() + ".txt"));
			String line;
			while ((line = listUsers.readLine()) != null) {
				String lessonId = listUsers.readLine();
				menuItem = new JMenuItem(line + lessonId, KeyEvent.VK_U);
				menu.add(menuItem);
				menuItem.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						removeAll();
						Lesson l = new Lesson(lessonId);
						add(l);
						l.revalidate();
						repaint();
					}
				});
			}
			listUsers.close();
		} catch (Exception e) {
			e.printStackTrace();
		}*/

		change = new Button("change user and pass");
		change.setActionCommand("change");
		change.setBounds(825, 300, 150, 50);

		registerLesson = new Button("REGISTER LESSON");
		registerLesson.setActionCommand("REGISTER LESSON");
		registerLesson.setBounds(625, 300, 150, 50);

		exit = new Button("SIGN OUT");
		exit.setActionCommand("SIGN OUT");
		exit.setBounds(1200, 30, 80, 30);

		registerLesson.addActionListener(new ButtonListenerChoosePage());
		change.addActionListener(new ButtonListenerChoosePage());
		exit.addActionListener(new ButtonListenerChoosePage());

		sP.setLayout(null);
		sP.add(menuBar);
		sP.add(registerLesson);
		sP.add(change);
		sP.add(exit);
		add(sP);

	}

	private class ButtonListenerChoosePage implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getActionCommand().equals("change")) {
				removeAll();
				changePassUser cl = new changePassUser();
				add(cl);
				cl.revalidate();
				repaint();
			} else if (event.getActionCommand().equals("REGISTER LESSON")) {
				removeAll();
				RegisterLessonSt rlSt= new RegisterLessonSt();
				add(rlSt);
				rlSt.revalidate();
				repaint();
			} else if (event.getActionCommand().equals("SIGN OUT")) {
				try {
					signOut();
				} catch (IOException e) {
					e.printStackTrace();
				}
				removeAll();
				Login login = new Login();
				add(login);
				login.revalidate();
				repaint();
			}
		}
	}//for button


	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(StudentPage, 0, 0, null);
		g.drawImage(stPage, 500, 20, null);
	}//draw the images

}
