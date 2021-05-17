package acters;

import java.util.*;

import enums.Faculty;
import enums.Role;
import enums.Title;
import resources.Course;
import resources.CourseFile;
import resources.Mark;
import resources.Wsp;

/**
 * Represents a teacher enrolled in the university.
 * A teacher can be enrolled in many courses.
 */
@SuppressWarnings("serial")
public class Teacher extends Employee {
	/** Title of Teacher
	*/
	private Title title;
	/** Faculty where teacher teaches
	*/
	private Faculty faculty;
	/** List of student for each course
	*/
	private HashMap<Course, HashSet<Student> > courseStudents;
	/** messages 
	*/
	private Vector<String> messages;
	/**Constructor using super constructor from Employee
	 * @param name
	 * @param surname
	 * @param password
	 * @param role
	 * @param salary
	 */
	public Teacher(String name, String surname, String password, double salary, Title title,
			Faculty faculty) {
		super(name, surname, password, Role.TEACHER, salary);
		courseStudents = new HashMap<Course, HashSet<Student>>();
		this.faculty = faculty;
		this.title = title;
		messages = new Vector<String>();
	}
	/**
	 * @return the faculty
	 */
	public Faculty getFaculty() {
		return faculty;
	}
	/**
	 * @param faculty the faculty to set
	 */
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	/**
	 * @return the title
	 */
	public Title getTitle() {
		return title;
	}
	/**
	 * @return the courseStudents
	 */
	public HashMap<Course, HashSet<Student>> getCourseStudents() {
		return courseStudents;
	}
	/**
	 * @return courseStudents.keySet() - courses
	 */
	public Set<Course> getCourses(){
		return courseStudents.keySet();
	}
	/**
	 * @return the messages
	 */
	public Vector<String> getMessages() {
		return messages;
	}
	/** Add new course 
	 * @param course - new course
	 */
	public void addCourse(String courseName) {
		for(Course course : Wsp.getInstance().getCourses())
			if(course.getName().equals(courseName)) {
				courseStudents.put(course, new HashSet<Student>());
				for(User user : Wsp.getInstance().getUsers()) {
					if(user instanceof Student) {
						if(((Student) user).ifHasCourse(course)) {
							courseStudents.get(course).add((Student) user);
						}
					}
				}
			}
	}

	/** Add new course file for course 
	 * @param courseFile
	 * @param courseName
	 */
	public void addCourseFile(String courseName, CourseFile courseFile) {
		for(Map.Entry<Course,HashSet<Student>> cm : courseStudents.entrySet())
			if(cm.getKey().getName().equals(courseName)){
				cm.getKey().addFile(courseFile);
			}
	}
	
	/** Delete course file of course
	 * @param courseFile
	 * @param course
	 */
	public void deleteCourseFile(Course course, CourseFile courseFile) {
		course.deleteFile(courseFile);
	}
	
	/** View file of course
	 * @param course
	 */
	public Vector<CourseFile> getCourseFiles(Course course) {
		return course.getCourseFiles();
	}
	
	/** @return the list of courses
	 */
	@SuppressWarnings("unchecked")
	public Vector<Course> viewAllCourses() {
		return (Vector<Course>) Wsp.getInstance().getCourses();
	}
	/** Check if teacher has course with course Name
	 * @param courseName name of course
	 * @return true/false if Teacher has this course
	 */
	public boolean ifHasCourse(String courseName) {
		for(Map.Entry<Course, HashSet<Student> > cs : courseStudents.entrySet()) {
			if(cs.getKey().getName().equals(courseName)){
				return true;
			}
		}
		return false;
	}
	
	/** View students of course
	 * @param course
	 * @return the list of students for each course
	 */
	public HashSet<Student> getListOfStudents(Course course) {
		return courseStudents.get(course);
	}

	/**
	 * @return the message
	 */
	public String viewMessages() {
		String s = "";
		int i = 1;
		for(String message : messages) {
			s+= i + "." + message;
			i++;
		}
		return s;
	}
	/** Write new message 
	 * @param text - new message
	 */
	public void addMessage(String text) {
		messages.add(text);
	}
	/** Registr student to course
	 * @param coursename - name of course
	 * @param student - student that will be registred to course
	 */
	public void addStudentToCourse(String coursename, Student student) {
		for(Map.Entry<Course, HashSet<Student>> cs : courseStudents.entrySet())
			if(cs.getKey().getName().equals(coursename))
				courseStudents.get(cs.getKey()).add(student);
	}
	/** Return report histogram info about student's marks in course
	 * @param coursename - name of course
	 */
	public String viewStatistics(String coursename) {
		String statistics = "";
		String[] distributions = {"F : ","FX: ","D : ","D+: ","C-: ","C : ","C+: ","B-: ","B : ","B+: ","A-: ","A : "};
		HashSet<Student> studentsInCourse = null;
		for(Map.Entry<Course,HashSet<Student>> cs : courseStudents.entrySet()) {
			if(cs.getKey().getName().equals(coursename)) {
				studentsInCourse = this.getListOfStudents(cs.getKey());
				break;
			}
		}
        for ( Student student : studentsInCourse ) {
        	for(Map.Entry<Course, Mark> cm : student.getCourseMarks().entrySet()) {
        		if(cm.getKey().getName().equals(coursename)) {
        			Mark mark = cm.getValue();
        			if(mark.getTotalScore()>=95) distributions[11]+='*';
                	else if(mark.getTotalScore()>=90) distributions[10]+='*';
                    else if(mark.getTotalScore()>=85) distributions[9]+='*';
                    else if(mark.getTotalScore()>=80) distributions[8]+='*';
                    else if(mark.getTotalScore()>=75) distributions[7]+='*';
                    else if(mark.getTotalScore()>=70) distributions[6]+='*';
                    else if(mark.getTotalScore()>=65) distributions[5]+='*';
                    else if(mark.getTotalScore()>=60) distributions[4]+='*';
                    else if(mark.getTotalScore()>=55) distributions[3]+='*';
                    else if(mark.getTotalScore()>=50) distributions[2]+='*';
                    else if(mark.getTotalScore()>=30) distributions[1]+='*';
                    else distributions[0]+='*';
        		}
        	}	
        }
        statistics += "Mark statistics about students in course " + coursename + "\n";
        for (String distribution : distributions) {
			statistics += "\n" + distribution;
		}
        return statistics;
	}
	
	/** Return info about student in course
	 * @param name - name of student
	 * @param surname - surname of student
	 */
	public String getInfoStudent(String name,String surname) {
		for(Map.Entry<Course, HashSet<Student>> cs : courseStudents.entrySet()) {
			for(Student student : cs.getValue()) {
				if(student.getName().equals(name) && student.getSurname().equals(surname)){
					return student.viewInfo();
				}
			}
		}
		return null;
	}
	/** Return info about teacher in course
	 * @return info info about teacher
	 */
	public String viewInfo() {
		String info = "";
		info += super.viewInfo() + "\nTeacherTitle: " + this.getTitle().toString();
		info += "\nFaculty: " + this.getFaculty().toString();
		return info;
	}
	/** Put first attestation mark for student in course
	 * @param student 
	 * @param course
	 * @param value mark of first attestation
	 */
	public void putFirstAttestationMark(Student student,Course course,double value) {
		student.setFirstAttestation(course, value);
	}
	/** Put second attestation mark for student in course
	 * @param student 
	 * @param course
	 * @param value mark of second attestation
	 */
	public void putSecondAttestationMark(Student student,Course course,double value) {
		student.setSecondAttestation(course, value);
	}
	/** Put final mark for student in course
	 * @param student 
	 * @param course
	 * @param value mark of final
	 */
	public void putFinalMark(Student student,Course course,double value) {
		student.setFinal(course, value);
	}
	
	/**
	 * @return courseStudents.keySet() - number of courses
	 */
	public int getCoursesNumber(){
		return courseStudents.keySet().size();
	}
	
}
