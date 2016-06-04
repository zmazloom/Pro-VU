import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.imageio.ImageIO;

public class changePassUser extends User{

	private Button changePassButton, changeUserButton, back;
	private JLabel changePassLabel, changeUserLabel, result;
	private JTextField changePass, changeUser;
	private Image changeImage, imageChange;
	private JPanel chPU;

	public changePassUser(){
		try {
			changeImage = ImageIO.read(getClass().getResource("/image/page.jpg"));
			imageChange = ImageIO.read(getClass().getResource("/image/changeUserPass.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		chPU = new JPanel();
		chPU.setPreferredSize(new Dimension(1400, 700));
		chPU.setBounds(0, 0, 1400, 700);
		chPU.setOpaque(false);

		changePassLabel = new JLabel("enter you new password :");
		changePassLabel.setBounds(550, 250, 200, 30);
		changePass = new JTextField (10);
		changePass.setBounds(600, 290, 200, 30);
		changePassButton = new Button("CHANGE PASSWORD");
		changePassButton.setBounds(625, 330, 150, 30);
		changePassButton.setActionCommand("CHANGE PASSWORD");

		changeUserLabel = new JLabel("enter you new username :");
		changeUserLabel.setBounds(550, 370, 200, 30);
		changeUser = new JTextField (10);
		changeUser.setBounds(600, 410, 200, 30);
		changeUserButton = new Button("CHANGE USERNAME");
		changeUserButton.setBounds(625, 450, 150, 30);
		changeUserButton.setActionCommand("CHANGE USERNAME");

		back = new Button("BACK");
		back.setBounds(1250, 20, 100, 40);
		back.setActionCommand("BACK");

		result = new JLabel("");
		result.setBounds(550, 200, 200, 30);

		changePassButton.addActionListener(new ButtonListener());
		changeUserButton.addActionListener(new ButtonListener());
		back.addActionListener(new ButtonListener());

		chPU.setLayout(null);
		chPU.add(changePassLabel);
		chPU.add(changePass);
		chPU.add(changePassButton);
		chPU.add(changeUserLabel);
		chPU.add(changeUser);
		chPU.add(changeUserButton);
		chPU.add(back);
		chPU.add(result);

		add(chPU);
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String who;
			try{
				who = getsOrt();
			}catch(IOException e){
				who = "professor";
			}
			if (event.getActionCommand().equals("BACK")){
				if (who.equals("professor")){
					ChoosePageProfessor chpp = new ChoosePageProfessor();
					panel.removeAll();
					panel.add(chpp);
					panel.revalidate();
					panel.repaint();
				}
				else{
					ChoosePageStudent chps = new ChoosePageStudent();
					panel.removeAll();
					panel.add(chps);
					panel.revalidate();
					panel.repaint();
				}
			}
			else {
				if (event.getActionCommand().equals("CHANGE PASSWORD")){
					String newPass;
					newPass = changePass.getText();
					if (newPass.equals("")) {
						result.setText("empty text field !");
					}
					else {
						try{
							changePassword(newPass);
							changePass.setText("");
							result.setText("Passwored changed successfully !");
						}catch(IOException ex){
						}
					}
				}
				if (event.getActionCommand().equals("CHANGE USERNAME")){
					String newUser;
					newUser = changeUser.getText();
					if (newUser.equals("")) {
						result.setText("empty text field !");
					}
					else {
						try{
							changeUsername(newUser);
							changeUser.setText("");
							result.setText("Username changed successfully !");
						}catch(IOException ex){
						}
					}
				}
			}
		}
	}//for button

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(changeImage, 0, 0, null);
		g.drawImage(imageChange, 550, 30, null);
    }//draw the images

}