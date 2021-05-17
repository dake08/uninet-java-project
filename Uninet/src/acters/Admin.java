package acters;

import java.util.*;

import enums.Role;
import resources.Wsp;

/**
 * Represents an admin enrolled in the university.
 */
@SuppressWarnings("serial")
public class Admin extends Employee {
	
	/** Constructs admin object
	 */
	public Admin(String name, String surname, String password, double salary) {
		super(name, surname, password, Role.ADMIN, salary);
	}

	/** Add user to Uninet's system
	 * @param user - added user
	 */
	public void addUser(User user) {
		Wsp.getInstance().addUser(user);
	}
	
	/** Delete user from system
	 * @param user - deleted user
	 */
	public void deleteUser(User user) {
		Wsp.getInstance().deleteUser(user);
	}

	/** Return list of users in Uninet
	 * @return HashSet of users in Uninet
	 */
	public HashSet<User> viewListOfUsers() {
		return Wsp.getInstance().getUsers();
	}

	/** Set password to user
	 * @param password - new password of user
	 * @param username - name of user
	 */
	public void setPassword( String username, String newPassword) {
		for(User user : Wsp.getInstance().getUsers())
			if(user.getName().equals(username))
				user.setPassword(newPassword);
	}
	
	/** View profile of user
	 * @param username - username of user
	 * @return user.viewInfo() - user's info
	 */
	public String viewProfile(String username) {
		for(User user : Wsp.getInstance().getUsers()) {
			if(user.getLogin().equals(username))
				return user.viewInfo();
		}
		return "User with username " + username + " doesn't exits";
	}
}
