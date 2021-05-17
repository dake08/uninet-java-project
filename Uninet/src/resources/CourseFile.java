package resources;

import java.io.Serializable;

import acters.Teacher;

/**
 */
@SuppressWarnings("serial")
public class CourseFile implements Serializable{
    /**
     * Field name of File
     */
    private String name;

    /** Field sender of file
     */
    private Teacher sender;
    
    public CourseFile(String name) {
    	this.name = name;
    }
    /**
     * @return the name of file
     */
    public String getNameOfFile() {
        return name;
    }

    /**
     * @return the sender 
     */
    public Teacher getSender() {
        return sender;
    }

    public String toString() {
    	return this.getNameOfFile();
    }
    public boolean equals(Object obj) {
		if(this == obj) 
			return true;
		if(obj == null)
			return false;
		if(this.getClass() != obj.getClass())
			return false;
		CourseFile c = (CourseFile) obj;
		return this.getNameOfFile().equals(c.getNameOfFile());
	}
	public int hashCode() {
		int result = 31;
		result = 31 * result + this.getNameOfFile().hashCode();
		return result;
	}
}

