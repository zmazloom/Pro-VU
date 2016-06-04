import java.io.*;

public class Student extends User {

	public int registerLesson(String lessonId, String userPr) throws IOException{
		String name = "lessons" + userPr + ".txt";
		BufferedReader isLesson = new BufferedReader(new FileReader(name));
		String registerName = lessonId + userPr + "studentname" + ".txt";
		BufferedReader isStudent = new BufferedReader(new FileReader(registerName));
		PrintWriter register = new PrintWriter( new BufferedWriter(new FileWriter(registerName, true)));
		String line;
		while ((isLesson.readLine()) != null) {
			if ((isLesson.readLine()).equals(lessonId)){
				while ((line = isStudent.readLine()) != null) {
					if (getUsername().equals(line)) {
						isLesson.close();
						isStudent.close();
						register.close();
						return 0;
					}
				}
				register.println(getUsername());
				register.flush();
				register.close();
				isLesson.close();
				isStudent.close();
				return 1;
			}
		}
		isLesson.close();
		isStudent.close();
		register.close();
		return -1;
	}//-1 : lesson not exist, 0 : student register in that lesson in past, 1 : register successfully

}