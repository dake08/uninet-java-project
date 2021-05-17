package acters;

import java.io.Serializable;
import enums.Role;
import exceptions.InvalidAccountException;
import exceptions.InvalidPasswordException;
import resources.Wsp;

/**
 * Represents an user enrolled in the university system.
 * An user will be enrolled with specific role.
 */
@SuppressWarnings({ "rawtypes", "serial" })
public abstract class User implements Comparable,Serializable{
	/** Field login of user
	 */
	private String login;

	/** Field name of user
	 */
	private String name;
	
	/** Field surname of user
	 */
	private String surname;

	/** Field password of user
	 */
	private String password;
 
	/** Field role of user
	 */
	private Role role;

	/** 
	 * @param oldPassword
	 * @param newPassword
	 */
	
	/** Constructor - create new User
	 * @param name
	 * @param surname
	 * @param password 
	 * @param role
	*/
	public User(String name, String surname, String password, Role role) {
		this.login = Wsp.getInstance().generateUserLogin(name, surname);
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.role = role;
	}
	/**  
	 * @param login - new login 
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password - new password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return th role of user
	 */
	public Role getRole() {
		return role;
	}
	/**
	 * @param role - new role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param surname - new surname 
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/** This method is using to change password.
	 * @param oldPassword - old password
	 * @param newPassword - new password
	 */
	public void changePassword(String oldPassword, String newPassword) throws InvalidPasswordException{
		if (password.equals(oldPassword)) {
			if (newPassword.equals(oldPassword)) {
				throw new InvalidPasswordException("Your enterred newPassword is the same as actual");
			} else
				this.setPassword(newPassword);
		}
		else {
			throw new InvalidPasswordException("Your enterred oldPassword is wrong");
		}
	}
	
	/** This method is using to check account with enterredLogin and enterredPassword exists. 
	 */
	public boolean enterAccount(String enterredLogin, String enterredPassword) throws InvalidAccountException {
		if (login.equals(enterredLogin) && password.equals(enterredPassword)) {
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * @return the name 
	 */

	public String getName() {
		return this.name;
	}

	/**
	 * @return the surname 
	 */
	public String getSurname() {
		return this.surname;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return this.login;
	}

	/** Compares this User with the specified object for order
	 */
	public int compareTo(Object obj) {
		User u = (User) obj;
		if (this.getName().compareTo(u.getName()) != 0)
			return this.getName().compareTo(u.getName());
		return this.getSurname().compareTo(u.getSurname());
	}
	/** View all info about this user
	 * @return info about user
	 */
	public String viewInfo() {
		return "\nName: " + this.name + "\nSurname: " + this.surname + "\nRole: " + this.getRole();
	}
	/**
	 * Indicates whether some other object is "equal to" this one
	 */
	public boolean equals(Object obj) {
		if(this == obj) 
			return true;
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		User u = (User) obj;
		return this.getLogin().equals(u.getLogin()) && this.getRole() == u.getRole();
	}
	/** Returns a hash code value for the user
	 */
	public int hashCode() {
		int result = 31;
		result = (int) (31 * result + this.getLogin().hashCode());
		return result;
	}
}
