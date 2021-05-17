package menu;

import java.io.*;

import acters.Student;
import acters.User;
import resources.*;

/**
 * Represents student's menu.
 */
public class StudentMenu implements Menu {
	/** Current user student in wsp
	 */
	private Student student;
	/**
	 * @param user - student
	 */
	public StudentMenu(User user) {
		this.student = (Student) user;
	}
	/** Show availiable courses for student 
	 */
	public String showAvailiableCourses() {
		String courseList= "";
		int i = 1;
		for(Course course : Wsp.getInstance().getCourses(student.getYear())){
			courseList += "\n " + i + ". "+course.getName();
			i++;
		}
		return courseList;
	}
	/**
	 * Register for course
	 * @param courseNumber - number of course
	 */
	public void registerForCourse(int courseNumber) {
		int i = 1;
		for(Course course : Wsp.getInstance().getCourses(student.getYear())){
			if(i == courseNumber) {
				student.registerCourse(course.getName());
				System.out.println("You successfully registered to course: " + course.getName());
			}
			i++;
		}
	}
	/**
	 * Start the menu of student. 
	 */
	public void startMenu() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		try {
			menu: while(true) {
				System.out.println("Which option do you choose?");
				System.out.println("\n 1)Get personal data \n 2.Registration for course \n 3.View Courses \n 4.View Course Files \n 5.View Marks \n 6.Exit");
				int choice = Integer.parseInt(reader.readLine());
				if(choice == 1) {
					System.out.println(student.viewInfo());
					System.out.println("\n 1) Return back \n 2) Exit");
					choice = Integer.parseInt(reader.readLine());
					if(choice == 1) continue menu;
					if(choice == 2) {System.out.println("Goodbye!!! Saving data..."); Wsp.getInstance().save(); break menu;}
					break;
				}
				else if(choice == 2) {
					registrForCourse: while(true) {
						System.out.println("Choose course to registr: ");
						System.out.println(this.showAvailiableCourses());
						choice = Integer.parseInt(reader.readLine());
						this.registerForCourse(choice);
						System.out.println("\n 1) Register to another course  \n 2) Return back \n 3) Exit");
						choice = Integer.parseInt(reader.readLine());
						if(choice == 1) continue registrForCourse;
						if(choice == 2) continue menu;
						if(choice == 3) {System.out.println("Goodbye!!! Saving data..."); Wsp.getInstance().save();break menu;}
						break;
					}
				}
				else if(choice == 3) {
					System.out.println(student.viewCourses());
					System.out.println("\n 1) Return back \n 2) Exit");
					choice = Integer.parseInt(reader.readLine());
					if(choice == 1) continue menu;
					if(choice == 2) {System.out.println("Goodbye!!! Saving data..."); Wsp.getInstance().save(); break menu;}
					break;
				}
				else if(choice == 4) {
					System.out.println(student.viewCourseFiles());
					System.out.println("\n 1) Return back \n 2) Exit");
					choice = Integer.parseInt(reader.readLine());
					if(choice == 1) continue menu;
					if(choice == 2) {System.out.println("Goodbye!!! Saving data..."); Wsp.getInstance().save(); break menu;}
					break;
				}
				else if(choice == 5) {
					System.out.println(student.viewCoursesMarks());
					System.out.println("\n 1) Return back \n 2) Exit");
					choice = Integer.parseInt(reader.readLine());
					if(choice == 1) continue menu;
					if(choice == 2) {System.out.println("Goodbye!!! Saving data..."); Wsp.getInstance().save(); break menu;}
					break;
				}
				else if(choice == 6) {
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
}

