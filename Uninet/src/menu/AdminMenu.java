package menu;

import enums.*;
import resources.*;

import java.io.*;

import acters.Admin;
import acters.Manager;
import acters.Student;
import acters.Teacher;
import acters.TechSupportGuy;
import acters.User;

/** Represents admin menu
 */
public class AdminMenu implements Menu{
	private Admin admin;

	public AdminMenu(User user) {
		this.admin = (Admin) user;
	}
	public String showAllUsers() {
		String usersList = "";
		int i = 1;
		for(User user: Wsp.getInstance().getUsers()) {
			usersList += "\n" + i + "." + user.viewInfo();
			i++;
		}
		return usersList;
	}
	public User getUser(String name,String surname,String password,Role type) throws IOException {
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		switch(type) {
		case ADMIN:
			System.out.println("Enter salary of admin");
			int salary = Integer.parseInt(read.readLine());
			return new Admin(name,surname,password,salary);
		case TEACHER:
			System.out.println("Enter salary of teacher");
			salary = Integer.parseInt(read.readLine());
			System.out.println("Enter title of teacher");
			Title title = Title.valueOf(read.readLine());
			System.out.println("Enter faculty of teacher");
			Faculty faculty = Faculty.valueOf(read.readLine());
			return new Teacher(name,surname,password,salary,title,faculty);
		case MANAGER:
			System.out.println("Enter salary of teacher");
			salary = Integer.parseInt(read.readLine());
			return new Manager(name,surname,password,salary);
		case STUDENT:
			System.out.println("Enter faculty of student");
			faculty = Faculty.valueOf(read.readLine());
			System.out.println("Enter speciality of student");
			String speciality = read.readLine();
			System.out.println("Enter year of study of student");
			Year year = Year.tranferFromIntToYear(Integer.parseInt(read.readLine()));
			return new Student(name,surname,password,faculty,speciality,year);
		case TECHSUPPORT:
			return new TechSupportGuy(name,surname,password);
		default:
			break;
		}
		return null;
	}
	public void startMenu() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu: while(true) {
				System.out.println("Which option do you choose?");
				System.out.println("\n 1.View/Manage all users list in Intranet System\n 2.Exit");
				int choice = Integer.parseInt(reader.readLine());
				if(choice == 1) {
					System.out.println(this.showAllUsers());
					System.out.println("\n 1.Add user \n 2.Delete user\n 3.Change password\n 4. Return back \n 5. Exit");
					choice = Integer.parseInt(reader.readLine());
					if(choice == 1) {
						addUser: while(true) {
							System.out.println("Enter name of user");
							String name = reader.readLine();
							System.out.println("Enter surname of user");
							String surname = reader.readLine();
							System.out.println("Enter password of user");
							String password = reader.readLine();
							System.out.println("Choose type of user: ");
							System.out.println(Role.getTypeOfUsers());
							int typeNumber = Integer.parseInt(reader.readLine());
							Role curRoleOfUser = Role.chooseRole(typeNumber);
							User newUser = this.getUser(name, surname, password, curRoleOfUser);
							Wsp.getInstance().addUser(newUser);
							System.out.println("User " + newUser.getLogin() + " successfully created");
							System.out.println("\n 1) Add user again \n 2) Return back \n 3) Exit");
							choice = Integer.parseInt(reader.readLine());
							if(choice == 1) continue addUser;
							if(choice == 2) continue menu;
							if(choice == 3) {System.out.println("Goodbye!!! Saving data..."); Wsp.getInstance().save();break menu;}
							break;
						}
					}
					if(choice == 3) {
						
					}
					if(choice == 4) {
						
					}
					if(choice == 4) continue menu;
					if(choice == 5) {System.out.println("Goodbye!!! Saving data..."); Wsp.getInstance().save(); break menu;}
					break;
				}
//				else if(choice == 2) {
//					registrForCourse: while(true) {
//						System.out.println("Choose course to registr: ");
//						int i = 1;
//						for(Course course : Wsp.getInstance().getCourses(student.getYear())){
//							System.out.println("\n " + i + ". "+course.getName());
//							i++;
//						}
//						choice = Integer.parseInt(reader.readLine());
//						i = 1;
//						for(Course course : Wsp.getInstance().getCourses(student.getYear())){
//							if(i == choice) {
//								student.registerCourse(course.getName());
//								System.out.println("You successfully registered to course: " + course.getName());
//							}
//							i++;
//						}
//						System.out.println("\n 1) Register to another course  \n 2) Return back \n 3) Exit");
//						choice = Integer.parseInt(reader.readLine());
//						if(choice == 1) continue registrForCourse;
//						if(choice == 2) continue menu;
//						if(choice == 3) {System.out.println("Goodbye!!! Saving data..."); Wsp.getInstance().save();break menu;}
//						break;
//					}
//				}
//				else if(choice == 3) {
//					System.out.println(student.viewCourses());
//					System.out.println("\n 1) Return back \n 2) Exit");
//					choice = Integer.parseInt(reader.readLine());
//					if(choice == 1) continue menu;
//					if(choice == 2) {System.out.println("Goodbye!!! Saving data..."); Wsp.getInstance().save(); break menu;}
//					break;
//				}
//				else if(choice == 4) {
//					System.out.println(student.viewCourseFiles());
//					System.out.println("\n 1) Return back \n 2) Exit");
//					choice = Integer.parseInt(reader.readLine());
//					if(choice == 1) continue menu;
//					if(choice == 2) {System.out.println("Goodbye!!! Saving data..."); Wsp.getInstance().save(); break menu;}
//					break;
//				}
//				else if(choice == 5) {
//					System.out.println(student.viewCoursesMarks());
//					System.out.println("\n 1) Return back \n 2) Exit");
//					choice = Integer.parseInt(reader.readLine());
//					if(choice == 1) continue menu;
//					if(choice == 2) {System.out.println("Goodbye!!! Saving data..."); Wsp.getInstance().save(); break menu;}
//					break;
//				}
				else if(choice == 2) {
					System.out.println("Bye!!!"); 
					Wsp.getInstance().save();
					break menu;
				}
			}
//			System.exit(0);
		}
		catch(Exception e) {
			System.out.println("Oh, something wrong happened..");
			e.printStackTrace();
			Wsp.getInstance().save();
		}
			
	}
//	
}
