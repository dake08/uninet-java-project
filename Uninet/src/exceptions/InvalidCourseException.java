package exceptions;

@SuppressWarnings("serial")
public class InvalidCourseException extends Exception{
	/** Constructs a new exception with null as its detail message. 
     */
    public InvalidCourseException() {
    	super("Invalid course");
    }
    /** Constructs a new exception with the specified detail message.
     * @param message 
     */
    public InvalidCourseException(String message) {
    	super("Invalid course: " + message);
    }
}
