package exceptions;

/**
 */
@SuppressWarnings("serial")
public class InvalidAccountException extends Exception{
    /** Constructs a new exception with null as its detail message. 
     */
    public InvalidAccountException() {
    	super("Invalid account");
    }
    /** Constructs a new exception with the specified detail message.
     * @param message 
     */
    public InvalidAccountException(String message) {
    	super("Invalid account: " + message);
    }
}

