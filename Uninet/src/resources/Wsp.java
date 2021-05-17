package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import acters.Student;
import acters.User;
import exceptions.InvalidAccountException;

/** Represents Single Wsp Intranet System Class
 */
public class Wsp{
    /** We used Singleton pattern, because there can be only one Uninet.
     */
	private static final Wsp INSTANCE = new Wsp();
	/**
	 * changed public constructor to private
	 */
	private Wsp() {
		users = new HashSet<User>();
		courses = new HashMap<Course,HashSet<Student>>();
		ITOrders = new Vector<Order>();
	};
	/**
	 * The getter of Uninet
	 * @return INSTANCE - Uninet object
	 */
	public static Wsp getInstance() {
		return INSTANCE;
	}
	/**
	 * All unique users in Uninet
	 */
    public HashSet<User> users;
    /**
     * All unique courses with students list
     */
    public HashMap<Course,HashSet <Student> > courses;
    /**
     * IT orders in system
    */
    public Vector<Order> ITOrders;
    
    private FileInputStream fis;
	private FileOutputStream fos;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	/**
     * Getter of courses. Course is keyset of HashMap<Course,Vector<Student> >
    */
    public Set<Course> getCourses(){
    	return courses.keySet();
    }
    /** Get courses of certain year of study.
    */
    public Set<Course> getCourses(int yearOfStudy){
    	Set<Course>res = new HashSet<Course>();
    	for(Course course : courses.keySet()) {
    		if(course.getYearOfStudy() == yearOfStudy) {
    			res.add(course);
    		}
    	}
    	return res;
    }
    /**
     * Getter of users
    */
    public HashSet<User> getUsers(){
    	return users;
    }
    /**
     * Getter of ITorders
    */
    public Vector<Order> getITorders(){
    	return ITOrders;
    }
    /**
     * @param - course that will be added to Uninet
    */
    public void addCourse(Course course) {
    	courses.put(course,new HashSet<Student>());
    }
    /**
     * @param course that will be deleted from Uninet
     */
    public void deleteCourse(Course course) {
    	courses.remove(course);
    }
    /**
     * @param user that will be deleted from Uninet
     */
    public void deleteUser(User user) {
    	users.remove(user);
    }
    /**
     * @param user that will be added to Uninet
     */
    public void addUser(User user) {
    	users.add(user);
    }
    /**
     * This method generate Login for User
     * @param name - name of Student
     * @param username - surname of Student
     * @return login -  generated from name(first char) and surname in lower case
     */
    public String generateUserLogin(String name,String surname) {
		String log = name.substring(0, 1).toLowerCase() + '_' + surname.toLowerCase();
		return log;
	}
    @SuppressWarnings("unchecked")
	public void desCourses() throws IOException, ClassNotFoundException{
		fis = new FileInputStream("courses");
		ois = new ObjectInputStream(fis);
		courses = (HashMap<Course,HashSet<Student> >) ois.readObject();
	}
	public void serCourses()throws IOException{
		fos = new FileOutputStream("courses");
		oos = new ObjectOutputStream(fos);
		oos.writeObject(courses);
		oos.close();
	}
	@SuppressWarnings("unchecked")
	public  void desUsers() throws IOException, ClassNotFoundException{
		fis = new FileInputStream("users");
		ois = new ObjectInputStream(fis);
		users = (HashSet<User>) ois.readObject();
	}
	
	public void serUsers()throws IOException{
		fos = new FileOutputStream("users");
		oos = new ObjectOutputStream(fos);
		oos.writeObject(users);
		oos.close();
	}
	@SuppressWarnings("unchecked")
	public void desOrders() throws IOException, ClassNotFoundException{
		fis = new FileInputStream("orders");
		ois = new ObjectInputStream(fis);
		ITOrders = (Vector<Order>) ois.readObject();
 	}
	public void serOrders() throws IOException{
		fos = new FileOutputStream("orders");
		oos = new ObjectOutputStream(fos);
		oos.writeObject(ITOrders);
		oos.close();
	}
	/**
	 * Returns user with username,password
	 * @param username
	 * @param password
	 * @return User object
	 * @throws InvalidAccountException
	 */
	public User loginUser(String username,String password) throws InvalidAccountException {
		for(User user : users) {
			if(user.enterAccount(username, password))
				return user;
		}
		System.out.println("Wrong password or login. Please try again.");
		return null;
	}
	/** Serialize Users,Courses,Orders
	 * @throws IOException
	 */
	public void save() throws IOException{
		serUsers();
		serCourses();
		serOrders();
	}	
}

