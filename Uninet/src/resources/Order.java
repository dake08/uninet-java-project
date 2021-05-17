package resources;

import java.io.Serializable;

/** Represents employee orders.
 */
@SuppressWarnings("serial")
public class Order implements Serializable {
	/** Order status
	 */
	private boolean isAccepted;
	/** Text of order
	 */
	private String orderText;
	/**  
	 */
	public Order(String orderText) {
		this.orderText = orderText;
		isAccepted = false;
	}
	/** @return the status of order
	 */
	public boolean getStatus() {
		return isAccepted;
	}
	/** Accept order and set order done. Number of done orders of TechSupportGuy increases by 1.
	 * @param order accepted/done order  
	 */
	public void setOrderAccepted() {
		isAccepted = true;
	}
	public String toString() {
		return this.orderText;
	}
}
