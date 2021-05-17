package acters;
import java.util.*;
import enums.Role;
import enums.Year;
import resources.Course;
import resources.CourseFile;
import resources.Mark;
import resources.Wsp;
import enums.Faculty;
/**
 * Represents a student enrolled in the university.
 * A student can be enrolled in many courses.
 */
@SuppressWarnings("serial")
public class Student extends User {
	/**
     * Integer year of study of a student.
     */
    private int yearOfStudy;

    /**
     * Faculty where student study. Faculty - enums with certain values(FIT,MKM etc.)
     */
    private Faculty faculty;
    private String speaciality;
    
    /**
     * HashMap<Course,Mark> store all courses in Set as key,and stundent's marks in different course as value.
     * Each mark belongs to one Course. Set(Course) will not allow student add duplicate courses.
     */
    private HashMap<Course, Mark> courseMarks;
    /**
	 * @return the courseMarks
	 */
	public HashMap<Course, Mark> getCourseMarks() {
		return courseMarks;
	}
	/**
     * Getter of the private field yearOfStudy
     */
    public int getYear() {
        return this.yearOfStudy;
    }
    /**
	 * Student inherits name,surname,password,role from User class;
	 * Role will be automatically Role.STUDENT
	 * And we create Student when student in first year
     */
    public Student(String name, String surname, String password,Faculty faculty,String speciality,Year year) {
		super(name, surname, password, Role.STUDENT);
		this.yearOfStudy = year.getYearOfStudy();
		this.faculty = faculty;
		this.speaciality = speciality;
		courseMarks = new HashMap<Course,Mark>();
	}
    /**
     * @return the faculty of student
     */
    public Faculty getFaculty() {
        return this.faculty;
    }

    /** This method add course for student
     * @param course The course student will register to
     */
    public void registerCourse(String courseName) {
    	for(Course course : Wsp.getInstance().getCourses()) {
    		if(course.getName().equals(courseName)) {
    			this.courseMarks.put(course, new Mark(0,0,0));
    		}
    	}
    	for(User user : Wsp.getInstance().getUsers()) {
    		if(user instanceof Teacher) {
    			if(((Teacher) user).ifHasCourse(courseName)) {
    				((Teacher) user).addStudentToCourse(courseName, this);
    			}
    		}
    	}	
    }
    
    
    /** View courses of student
     * @return courses.keySet() - current courses of student
     */
    public Set<Course> viewCourses() {
        return courseMarks.keySet();
    }
    /** View list of courses with marks
     * @return courses.keySet() - current courses of student
     */
    public String viewCoursesMarks() {
        String s = ""; 
        for (Map.Entry <Course,Mark> cm : courseMarks.entrySet()) {
           s += "Course: "+cm.getKey() + "marks :\n " + cm.getValue() + "\n";
        }
        return s;
    }
    
    /** Get grade of certain course 
     * @param courseName - name of course
     * @return totalScore - total score of student in course with courseName
     */
    public double getGrade(String courseName) {
        for (Map.Entry<Course,Mark> cm : courseMarks.entrySet()) {
        	if(cm.getKey().getName().equals(courseName)){
        		return cm.getValue().getTotalScore();
        	}
        }
        return 0;
    }

    
    /**
     * This method print all courseFiles of each courses
     * @return coursesFilesList - list of courses with files
     */
    public String viewCourseFiles() {
    	String coursesFilesList = "";
    	for(Course course : courseMarks.keySet()) {
    		System.out.println(course + " files:\n");
    		int i = 1;
    		for (CourseFile file : course.getCourseFiles()) {
    			coursesFilesList+= i + ". " + file +"\n" ;
    			i++;
    		}
    	}
    	return coursesFilesList;
    }
    
    /** Return all info about student
     * @return studentInfo - student info
     */
    public String viewInfo() {
    	String studentInfo = "";
    	studentInfo += super.viewInfo() + "\nYear of study: " + this.yearOfStudy;
    	studentInfo += "\nFaculty: " + this.faculty.toString();
    	return studentInfo;
    }
    /** Set first attestation mark
     */
    public void setFirstAttestation(Course course,double value) {
    	this.getCourseMarks().get(course).setFirstAttestationValue(value);
    }
    /** Set second attestation mark
     */
    public void setSecondAttestation(Course course,double value) {
    	this.getCourseMarks().get(course).setSecondAttestationValue(value);
    }
    /** Set final mark
     */
    public void setFinal(Course course,double value) {
    	this.getCourseMarks().get(course).setFinalMark(value);
    }
    
    /** This method shows course marks of student
     * @param course 
     * @return marks of course 
     */
    public String viewCourseMarks(Course course) {
    	if(this.courseMarks.get(course).toString() == null) {
    		return "Student doesn't have this course";
    	}
    	return this.courseMarks.get(course).toString();
    }
    
    
    /** Check if student has this course
     * @param course
     * @return true/false if student has course
     */
    public boolean ifHasCourse(Course course) {
		for(Map.Entry<Course, Mark > cm : courseMarks.entrySet()) {
			if(cm.getKey().equals(course)){
				return true;
			}
		}
		return false;
	}
    /**
     * @return totalGpa of student( 0.00 - 4.00 )
     */
    public double getTotalGpa() {
    	double totalGpa;
    	double totalMarks = 0;
    	for(Mark mark : this.getCourseMarks().values()) {
    		totalMarks += mark.getTotalScore();
    	}
    	totalGpa = totalMarks/this.viewCourses().size();
    	return totalGpa;
    }
    
}

