package acters;

import enums.Role;
import resources.Order;

/**
 * Represents an employee enrolled in the university.
 */

@SuppressWarnings("serial")
public abstract class Employee extends User {
	/**
	 * The month salary of employee in tenge
	 */
	private double salary;
	/**
	 * @param name,  @param surname, @param password, @param role inherits from User
	 * @param salary - define what salary will have employee
	 */
	public Employee(String name, String surname, String password, Role role, double salary) {
		super(name, surname, password, role);
		this.salary = salary;
	}
	/**
	 * @return salary - current salary of employee
	 */
	public double getSalary() {
		return this.salary;
	}
	/** @param salary - new salary of employee
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * This method add new order to order list
	 * @param orderText - Description of order
	 */
	public void sendOrderToIT(String orderText) {
		TechSupportGuy.addOrder(new Order(orderText));
	}
	
	/** View info of employee
	 * @return User.info + salary
	 */
	public String viewInfo() {
		return super.viewInfo() + "\nSalary: " + this.salary;
	}
}
