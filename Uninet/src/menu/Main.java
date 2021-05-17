package menu;

import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

import acters.Admin;
import acters.Manager;
import acters.Student;
import acters.Teacher;
import acters.User;
import enums.*;
import resources.*;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		if(new File("courses").exists()) {
			Wsp.getInstance().desCourses();
		}
		if(new File("users").exists()) {
			Wsp.getInstance().desUsers();
		}
		if(new File("orders").exists()) {
			Wsp.getInstance().desOrders();
		}
		Wsp wsp = Wsp.getInstance();
		Admin mainAdmin = new Admin("admin","admin","admin",1000);
		wsp.addUser(mainAdmin);
//		for(int i = 0; i < 200;i++) {
//			i++;
//			mainAdmin.addUser(new Student(String.valueOf(i),String.valueOf(i),"dakadaka",Faculty.FIT,"IS",Year.FIRST));
//		}
//		Manager manager = new Manager("Nargiza","Aidarkhan","manager",200);
//		manager.addCoursesForRegistration(new Course("OOP",Year.FIRST));
//		manager.addCoursesForRegistration(new Course("PP1",Year.FIRST));
//		manager.addCoursesForRegistration(new Course("Algo",Year.SECOND));
//		for(User user : Wsp.getInstance().users) {
//			if(user instanceof Student) {
//				Student student = (Student) user;
//				for(Course course : Wsp.getInstance().getCourses()){
//					student.registerCourse(course.getName());
//				}
//			}
//		}
//		for(User user : Wsp.getInstance().users) {
//			if(user instanceof Student) {
//				Student student = (Student) user;
//				for(Course course : student.getCourseMarks().keySet()) {
//					double random = ThreadLocalRandom.current().nextDouble(0, 30);
//					((Student) user).setFinal(course, random);
//					((Student) user).setFirstAttestation(course, random);
//					((Student) user).setSecondAttestation(course, random);
//				}
//			}
//		}
//		admin.addUser(new Student("Daulet","Tusupbai","dakadaka",Faculty.FIT,"IS"));
//		Teacher teacher = new Teacher("Pakita","OOP","pakita",200,Title.SENIORLECTOR,Faculty.FIT);
//		teacher.sendOrderToIT("Can't find marks of OOP");
//		admin.addUser(teacher);
//		admin.viewListOfUsers();
//		System.out.println(teacher.getLogin() + " " + teacher.getPassword());
//		
//		teacher.addCourse("OOP");
//		teacher.addCourseFile("OOP",new CourseFile("lecture1"));
//		teacher.addCourseFile("OOP",new CourseFile("lecture2"));
//		teacher.addCourseFile("OOP",new CourseFile("lecture3"));
//		teacher.addCourseFile("OOP",new CourseFile("lecture4"));
		try {
			System.out.println("Welcome to Intranet!");
			login : while(true) {
				System.out.println("Enter username to login:");
				String username = reader.readLine();
				System.out.println("Enter password to login:");
				String password = reader.readLine();
				User curUser = Wsp.getInstance().loginUser(username, password);
//				System.out.println(curUser.viewInfo());
				if(curUser == null) {
					continue login;
				}
				MenuFactory menuFactory = new MenuFactory();
				Menu menu = menuFactory.getMenu(curUser);
				if(menu != null) menu.startMenu();
			}
		}
		catch(Exception e) {
			System.out.println("Oh sorry something understandable happened error... \n Save resources?");
			e.printStackTrace();
			Wsp.getInstance().save();
			System.out.println("\n Goodbye");
		}
	}

}
