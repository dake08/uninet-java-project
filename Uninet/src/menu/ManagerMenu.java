package menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import acters.Manager;
import acters.Teacher;
import acters.User;
import enums.Faculty;
import enums.Year;
import resources.Course;
import resources.Wsp;

public class ManagerMenu implements Menu {
	private Manager manager;

	public ManagerMenu(User user) {
		this.manager = (Manager) user;
	}

	public void sendMessage(int num,String text) {
		int i = 1;
		for (User u : Wsp.getInstance().getUsers()) {
			if (u instanceof Teacher) {
				if(i == num) {
					manager.sendMessagesToTeacher(text, (Teacher) u);
				}
				i++;
			}
		}
	}
	public void startMenu() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu: while (true) {
				System.out.println("Which option do you choose?");
				System.out.println("\n 1)Add course for registration \n 2. View info about all students \n 3. View info about all teachers \n 4.Send message to teacher \n 5.Exit");
				int choice = Integer.parseInt(reader.readLine());
				if (choice == 1) {
					addCourse: while (true) {
						System.out.println("Enter name of course");
						String name = reader.readLine();
						System.out.println("Enter faculty of course");
						Faculty faculty = Faculty.valueOf(reader.readLine());
						System.out.println("Enter year of study of course");
						Year year = Year.tranferFromIntToYear(Integer.parseInt(reader.readLine()));
						Course newCourse = new Course(name,year,faculty);
						manager.addCoursesForRegistration(newCourse);
						System.out.println(newCourse + " successfully added");
						System.out.println("\n 1)Add another course \n 2.Return back \n 3.Exit");
						choice = Integer.parseInt(reader.readLine());
						if (choice == 1) {
							continue addCourse;
						}
						if (choice == 2) {
							continue menu;
						}
						if (choice == 3) {
							System.out.println("Goodbye!!! Saving data...");
							Wsp.getInstance().save();
							break menu;
						}
						break addCourse;
					}
				}
				if (choice == 2) {
					System.out.println(manager.viewStudentList());
				}
				if (choice == 3) {
					System.out.println(manager.viewTeachersList());
				}
				if (choice == 4) {
					sendMessage: while (true) {
						System.out.println(manager.viewTeachersList());
						System.out.println("Choose teacher to send message");
						int teach = Integer.parseInt(reader.readLine());
						System.out.println("Enter text of message");
						String name = reader.readLine();
						this.sendMessage(teach, name);
						System.out.println(name + " successfully added");
						System.out.println("\n 1) Send another message \n 2.Return back \n 3.Exit");
						choice = Integer.parseInt(reader.readLine());
						if (choice == 1) {
							continue sendMessage;
						}
						if (choice == 2) {
							continue menu;
						}
						if (choice == 3) {
							System.out.println("Goodbye!!! Saving data...");
							Wsp.getInstance().save();
							break menu;
						}
						break sendMessage;
					}
				}
				if (choice == 5) {
					System.out.println("Goodbye!!! Saving data...");
					Wsp.getInstance().save();
					break menu;
				}
			}
		} catch (Exception e) {
			System.out.println("Oh, something wrong happened..");
			e.printStackTrace();
			Wsp.getInstance().save();
		}
	}
}
