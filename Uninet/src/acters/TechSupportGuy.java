package acters;

import java.util.*;

import enums.Role;
import resources.Order;
import resources.Wsp;
/**
 * Represents a tech support guy enrolled in the university.
 * There is only one static general orders list for all guys
 */
@SuppressWarnings("serial")
public class TechSupportGuy extends User {
    /** Number of all accepted orders
     */
	private int numOfDoneOrders;
	/** Number of all accepted orders
     */
    public Vector<Order> getAllOrders() {
    	return Wsp.getInstance().ITOrders;
    }
    /** Constructor using super constructor from User
     * @param name
     * @param surname
     * @param password
     */
    public TechSupportGuy(String name, String surname, String password) {
		super(name, surname, password,Role.TECHSUPPORT);
		this.numOfDoneOrders = 0;
	}
	/** @return number of accepted/done orders
     */
    public int getNumOfDoneOrders() {
    	return this.numOfDoneOrders;
    }
    /** Method to view new orders, if order is not accepted.
     * @return new order
     */
    public String viewNewOrders() {
    	String s = "";
    	int i = 1;
    	for (Order order : Wsp.getInstance().ITOrders) {
    		if(order.getStatus() == false) {
    			s += "\n " + i + "." + order.toString();
    			i++;
    		}
    	}
    	return s;
    }
    
    /** Add order
     * @param order - new order 
    */
    public static void addOrder(Order order) {
    	Wsp.getInstance().ITOrders.add(order);
    }
    
    /**  Accept order
     * @param order - done/accepted order
     */
    public void acceptOrder(Order order) { 	
    	order.setOrderAccepted();
    	this.numOfDoneOrders++;
    }

    /** Reject/remove order 
     * @param order - rejected order 
     */
    public void rejectOrder(Order order) {
    	Wsp.getInstance().ITOrders.remove(order);
    }

    /** View done orders
     * @return the accepted/done orders
     */
    public String viewDoneOrders() {
    	String s = "";
    	int i = 1;
    	for (Order order : Wsp.getInstance().ITOrders) {
    		if(order.getStatus()) {
    			s += "\n " + i + "." + order.toString();
    			i++;
    		}
    	}
    	return s;
    }
    
    /**
     * Compare techguys by done orders
     */
    public int compareTo(Object o) {
    	TechSupportGuy t = (TechSupportGuy) o;
    	if(super.compareTo(t) == 0) {
    		if(this.getNumOfDoneOrders()>t.getNumOfDoneOrders()) {
    			return 1;
    		}
    		if(this.getNumOfDoneOrders()<t.getNumOfDoneOrders()) {
    			return -1;
    		}
    		return 0;
    	}
    	return super.compareTo(t);
    }
}

