import java.io.*;

public class Professor extends User {

	String user, name;

	public boolean addLesson(String lessonName, String id) throws IOException{
		String username = getUsername();
		String name = "lessons" + username + ".txt";
		PrintWriter lessons = new PrintWriter( new BufferedWriter(new FileWriter(name, true)));
		try{
			BufferedReader repetitive = new BufferedReader(new FileReader(name));
			String line;
			while ((repetitive.readLine()) != null) {
				if ((repetitive.readLine()).equals(id)){
					return false;
				}
			}
		}catch(IOException e){
			return false;
		}
		lessons.println(lessonName);
		lessons.println(id);
		lessons.flush();
		lessons.close();

		name = id + username + "studentname" + ".txt";
		PrintWriter idStudentName = new PrintWriter( new BufferedWriter( new FileWriter(name, true)));
		idStudentName.close();
		name = id + username + "post" + ".txt";
		PrintWriter idPost = new PrintWriter( new BufferedWriter( new FileWriter(name, true)));
		idPost.close();
		name = id + username + "practice" + ".txt";
		PrintWriter idPractice = new PrintWriter( new BufferedWriter( new FileWriter(name, true)));
		idPractice.close();

		return true;
	}//creat the lesson

	public String showStudents(String id) throws IOException{
		String name = id + getUsername() + "studentname" + ".txt";
		String lists = "";
		try {
			BufferedReader showS = new BufferedReader(new FileReader("name"));
			String line;
			while ((line = showS.readLine()) != null) {
				lists += line + "\r\n";
			}
			showS.close();
		} catch (IOException e) {
			return lists;
		}
		return lists;
	}

	public boolean removeStudent(String username, String id) throws IOException{
		int sw = 1;
		String name = id + getUsername() + "studentname" + ".txt";
		BufferedReader whereUser = new BufferedReader( new FileReader(name));
		String line;
		String text = "";
		while ((line = whereUser.readLine()) != null) {
			if (line.equals(username)==false){
				text += line + "\r\n";
			}
			else sw = 0;
		}
		if (sw==1) return false;// the Student not exist
		PrintWriter change = new PrintWriter( new BufferedWriter( new FileWriter(name)));
		change.print(text);
		change.flush();
		change.close();
		whereUser.close();
		return true;
	}

	public boolean addStudent(String username, String id) throws IOException{
		int sw = 1;
		String name = id + getUsername() + "studentname" + ".txt";
		BufferedReader whereUser = new BufferedReader( new FileReader(name));
		String line;
		while ((line = whereUser.readLine()) != null) {
			if (line.equals(username) == true) {
				whereUser.close();
				return false;//student exists.
			}
		}
		PrintWriter change = new PrintWriter( new BufferedWriter( new FileWriter(name, true)));
		change.println(username);
		change.flush();
		change.close();
		whereUser.close();
		return true;
	}

	public void addPost(String topic, String writer, String text, String id) throws IOException{
		String username = getUsername();
		name = id + username + "post" + ".txt";
		PrintWriter addpost = new PrintWriter( new BufferedWriter( new FileWriter(name, true)));
		addpost.println(topic);
		addpost.println("...!");
		addpost.println(writer);
		addpost.println("...!");
		addpost.println(text);
		addpost.println("...!");
		addpost.flush();
		addpost.close();
	}

	public void addPractice(String topic, String writer, String text, String data, String id) throws IOException{
		String username = getUsername();
		name = id + username + "practice" + ".txt";
		PrintWriter addpractice = new PrintWriter( new BufferedWriter( new FileWriter(name, true)));
		addpractice.println(topic);
		addpractice.println("...!");
		addpractice.println(writer);
		addpractice.println("...!");
		addpractice.println(data);
		addpractice.println("...!");
		addpractice.println(text);
		addpractice.println("\\e");
		addpractice.flush();
		addpractice.close();
	}

	/*public void editionPost(String topic, String text, String id){
		String username = getUsername();
		String name = id + username + "post" + ".txt";
		BufferedReader edition = new BufferedReader(new FileReader(name));
		String line;
		while ((edition.readLine()) != null) {
			if ((edition.readLine()).equals(topic)){
				//edition.
			}
		}

		String username = getUsername();

		PrintWriter addpost = new PrintWriter( new BufferedWriter( new FileWriter(name, true)));
		addpost.println(topic);
		addpost.println(writer);
		addpost.println(text);
		addpost.flush();
		addpost.close();
	}*/

}