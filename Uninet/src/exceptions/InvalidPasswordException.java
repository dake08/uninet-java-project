package exceptions;

@SuppressWarnings("serial")
public class InvalidPasswordException extends Exception{
	/** Constructs a new exception with null as its detail message. 
     */
    public InvalidPasswordException() {
    	super("Invalid password");
    }
    /** Constructs a new exception with the specified detail message.
     * @param message 
     */
    public InvalidPasswordException(String message) {
    	super("Invalid password: " + message);
    }
}
