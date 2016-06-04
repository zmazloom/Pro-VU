import java.io.*;
import javax.swing.*;

public class User extends VU{

	public int login(String user, String pass) throws IOException{
		try {
			PrintWriter onlineUser = new PrintWriter( new BufferedWriter( new FileWriter("onlineUser.txt")));
			BufferedReader listUsers = new BufferedReader( new FileReader("listUsers.txt"));
			String line;
			while ((line = listUsers.readLine()) != null) {
				if (line.equals(user)){
					if ((listUsers.readLine()).equals(pass)){
							onlineUser.println(user);
							onlineUser.println(pass);
							onlineUser.println(listUsers.readLine());
							onlineUser.println(listUsers.readLine());
							if ((line = listUsers.readLine()).equals("professor")){
								onlineUser.println(line);
								onlineUser.flush();
								onlineUser.close();
								listUsers.close();
								return 1;
							}
							else {
								onlineUser.println(line);
								onlineUser.flush();
								onlineUser.close();
								listUsers.close();
								return 2;
							}

					}
					else {
						listUsers.readLine();
						listUsers.readLine();
						listUsers.readLine();
					}
				}
				else {
					listUsers.readLine();
					listUsers.readLine();
					listUsers.readLine();
					listUsers.readLine();
				}
			}
		} catch (IOException e) {
			return 0;
		}
		return 0;
	}//0 for false, 1 for professor, 2 for student

	public boolean register(String name, String family, String user, String pass, String sOrPr) throws IOException{
		PrintWriter listUsers = new PrintWriter( new BufferedWriter( new FileWriter("listUsers.txt", true)));
		BufferedReader repetitive = new BufferedReader( new FileReader("listUsers.txt"));
		String line;
		while ((line = repetitive.readLine()) != null) {
			if (line.equals(user)){
				repetitive.close();
				return false;
			}
			repetitive.readLine();
			repetitive.readLine();
			repetitive.readLine();
			repetitive.readLine();
		}//check username is repetitive or not!

		listUsers.println(user);
		listUsers.println(pass);
		listUsers.println(name);
		listUsers.println(family);
		listUsers.println(sOrPr);
		listUsers.flush();
		listUsers.close();
		return true;
	}

	public String getUsername() throws IOException{
		BufferedReader get = new BufferedReader( new FileReader("onlineUser.txt"));
		String line;
		return get.readLine();
	}
	public String getPassword() throws IOException{
		BufferedReader get = new BufferedReader( new FileReader("onlineUser.txt"));
		String line;
		get.readLine();
		return get.readLine();
	}
	public String getNamePerson() throws IOException{
		BufferedReader get = new BufferedReader( new FileReader("onlineUser.txt"));
		String line;
		get.readLine();
		get.readLine();
		return get.readLine();
	}
	public String getFamily() throws IOException{
		BufferedReader get = new BufferedReader( new FileReader("onlineUser.txt"));
		String line;
		get.readLine();
		get.readLine();
		get.readLine();
		return get.readLine();
	}
	public String getsOrt() throws IOException{
		BufferedReader get = new BufferedReader( new FileReader("onlineUser.txt"));
		String line;
		get.readLine();
		get.readLine();
		get.readLine();
		get.readLine();
		return get.readLine();
	}
	public void changeUsername(String newUser) throws IOException{
		BufferedReader whatUser = new BufferedReader( new FileReader("onlineUser.txt"));
		String pastUser = whatUser.readLine();

		BufferedReader whereUser = new BufferedReader( new FileReader("listUsers.txt"));
		String line;
		String text = "";
		while ((line = whereUser.readLine()) != null) {
			if (line.equals(pastUser)){
				text += line.replaceAll(pastUser, newUser) + "\r\n";
			}
			else{
				text += line + "\r\n";
			}
			text += whereUser.readLine() + "\r\n" + whereUser.readLine() + "\r\n" + whereUser.readLine() + "\r\n" + whereUser.readLine() + "\r\n";
		}
		PrintWriter change = new PrintWriter( new BufferedWriter( new FileWriter("listUsers.txt")));
		change.print(text);
		change.flush();
		change.close();
		whereUser.close();
		String onlinText = pastUser.replaceAll(pastUser, newUser) + "\r\n" + whatUser.readLine() + "\r\n" + whatUser.readLine() + "\r\n" + whatUser.readLine() + "\r\n" + whatUser.readLine();
		whatUser.close();
		PrintWriter changeOnline = new PrintWriter( new BufferedWriter( new FileWriter("onlineUser.txt")));
		changeOnline.println(onlinText);
		changeOnline.flush();
		changeOnline.close();
	}

	public void changePassword(String newPass) throws IOException{
		BufferedReader whatPass = new BufferedReader( new FileReader("onlineUser.txt"));
		String onlinText = whatPass.readLine();
		String pastPass = whatPass.readLine();

		BufferedReader wherePass = new BufferedReader( new FileReader("listUsers.txt"));
		String line;
		String text = "";
		while ((line = wherePass.readLine()) != null) {
			text += line + "\r\n";
			line = wherePass.readLine();
			if (line.equals(pastPass)){
				text += line.replaceAll(pastPass, newPass) + "\r\n";
			}
			else{
				text += line + "\r\n";
			}
			text += wherePass.readLine() + "\r\n" + wherePass.readLine() + "\r\n" + wherePass.readLine() + "\r\n";
		}
		PrintWriter change = new PrintWriter( new BufferedWriter( new FileWriter("listUsers.txt")));
		change.print(text);
		change.flush();
		change.close();
		wherePass.close();
		onlinText += pastPass.replaceAll(pastPass, newPass) + "\r\n" + whatPass.readLine() + "\r\n" + whatPass.readLine() + "\r\n" + whatPass.readLine();
		whatPass.close();
		PrintWriter changeOnline = new PrintWriter( new BufferedWriter( new FileWriter("onlineUser.txt")));
		changeOnline.println(onlinText);
		changeOnline.flush();
		changeOnline.close();
	}

	public void signOut() throws IOException{
		String who = getUsername();
		PrintWriter signout = new PrintWriter( new BufferedWriter( new FileWriter("onlineUser.txt")));
		signout.print("");
		signout.flush();
		signout.close();
	}

}