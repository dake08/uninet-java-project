package resources;

import java.io.Serializable;
import java.util.*;
import enums.Faculty;
import enums.Year;

/** Represents course
*/
@SuppressWarnings("serial")
public class Course implements Serializable{
	
	/** Field name of course
	*/
	private String name;
	
	/** Field code of course
	*/
	private String code;
	/** Field credits of course
	*/
	private int totalCredits;
	
	/** Field description of course
	*/
	private String description;
	
	/** Field prerequisites of course
	*/
	private Vector<Course> prerequisites;
	
	/** Field faculty of course
	*/
	private Faculty faculty;
	
	/** Field files of course
	*/
	private Vector<CourseFile> courseFiles;
	
	/** Year of course
	*/
	private int yearOfStudy;

	/**
	 * @param name
	 * @param code
	 * @param totalCredits
	 * @param description
	 * @param prerequisites
	 * @param faculty
	 * @param courseFiles
	 * @param students
	 * @param yearOfStudy
	 */
	public Course(String name, String code, int totalCredits, String description, Vector<Course> prerequisites,
			Faculty faculty, Year yearOfStudy) {
		this.name = name;
		this.code = code;
		this.totalCredits = totalCredits;
		this.description = description;
		this.prerequisites = prerequisites;
		this.faculty = faculty;
		this.yearOfStudy = yearOfStudy.getYearOfStudy();
		this.courseFiles = new Vector<CourseFile>();
	}
	public Course(String name,Year yearOfStudy) {
		this.name = name;
		this.yearOfStudy = yearOfStudy.getYearOfStudy();
		this.courseFiles = new Vector<CourseFile>();
	}
	public Course(String name,Year yearOfStudy,Faculty faculty) {
		this.name = name;
		this.yearOfStudy = yearOfStudy.getYearOfStudy();
		this.courseFiles = new Vector<CourseFile>();
		this.faculty = faculty;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the courseFiles
	 */
	public Vector<CourseFile> getCourseFiles() {
		return courseFiles;
	}

	/**
	 * @return the totalCredits
	 */
	public int getTotalCredits() {
		return totalCredits;
	}

	/**
	 * @param totalCredits the totalCredits to set
	 */
	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}

	/**
	 * @return the prerequisites
	 */
	public Vector<Course> getPrerequisites() {
		return prerequisites;
	}

	/**
	 * @param prerequisites the prerequisites to set
	 */
	public void setPrerequisites(Vector<Course> prerequisites) {
		this.prerequisites = prerequisites;
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
	 * @return the yearOfStudy
	 */
	public int getYearOfStudy() {
		return yearOfStudy;
	}

	/**
	 * @param yearOfStudy the yearOfStudy to set
	 */
	public void setYearOfStudy(Year yearOfStudy) {
		this.yearOfStudy = yearOfStudy.getYearOfStudy();
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * @param courseFiles the courseFiles to set
	 */
	public void setCourseFiles(Vector<CourseFile> courseFiles) {
		this.courseFiles = courseFiles;
	}

	/**  @param course prerequisite course
	*/
	public void addPrerequisite(Course course) {
		prerequisites.add(course);
	}

	/** @param courseFile courseFile to be added
	 */
	public void addFile(CourseFile courseFile) {
		courseFiles.add(courseFile);
	}

	/** @param courseFile courseFile to be deleted
	 */
	public void deleteFile(CourseFile courseFile) {
		courseFiles.remove(courseFile);
	}
	/**
	 * Returns a string representation of the course
	 */
	public String toString() {
		return this.getName();
	}
	/** Courses equal if their name and year of study equal
	 * @param obj compared obj
	 */
	public boolean equals(Object obj) {
		if(this == obj) 
			return true;
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		Course c = (Course) obj;
		return this.getName().equals(c.getName()) && this.getYearOfStudy() == c.getYearOfStudy();
	}
	/**
	 * Returns a hash code value for the course
	 */
	public int hashCode() {
		int result = 31;
		result = (int) (31 * result + this.getYearOfStudy());
		result = 31 * result + this.getName().hashCode();
		return result;
	}

}
