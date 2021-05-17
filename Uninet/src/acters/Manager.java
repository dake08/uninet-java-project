package acters;

import enums.Role;
import resources.Course;
import resources.Wsp;

/**
 * Represents a manager enrolled in the university.
 */
@SuppressWarnings("serial")
public class Manager extends Employee {
	
	/** Add course to registragion(to Uninet's all courses)
	 * @param course
	 */
	public void addCoursesForRegistration(Course course) {
		Wsp.getInstance().addCourse(course);
	}
	
	/** Constructor using super constructor from Employee
	 * @param name
	 * @param surname
	 * @param password
	 * @param salary
	 */
	public Manager(String name, String surname, String password, double salary) {
		super(name, surname, password, Role.MANAGER, salary);
	}

	/** Return all students info in Uninet
	 * @return studentInfo - the list of students info
	 */
	public String viewStudentList() {
		String studentInfo = "";
		int i = 1;
		for (User u : Wsp.getInstance().getUsers()) {
			if (u instanceof Student) {
				studentInfo += i + "." + u.getName() + " " + u.getSurname() + "\n";
				i++;
			}
		}
		return studentInfo;
	}

	
	/** Add message to teachers mail
	 * @param messageText - message that will be sent to teacher
	 * @param teacher     - teacher that will receive message
	 */
	public void sendMessagesToTeacher(String messageText, Teacher teacher) {
		teacher.addMessage(messageText);
	}

	/** This method will return all teachers info in Uninet
	 * @return teachersInfo - the list of teachers info
	 */
	public String viewTeachersList() {
		String teacherInfo = "";
		int i = 1;
		for (User u : Wsp.getInstance().getUsers()) {
			if (u instanceof Teacher) {
				teacherInfo += i + "." +  u.getName() + " " + u.getSurname() + "\n";
				i++;
			}
		}
		return teacherInfo;
	}
}
